package bespoke.services;

import bespoke.config.kafka.InitRuleEngineDto;
import bespoke.config.kafka.KafkaProducer;
import bespoke.config.kafka.RuleEngineDto;
import bespoke.config.kafka.RuleEngineFourDto;
import bespoke.config.kafka.RuleEngineThreeDto;
import bespoke.config.kafka.RuleEngineTwoDto;
import bespoke.entities.Address;
import bespoke.entities.Condition;
import bespoke.entities.Criterion;
import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.Person;
import bespoke.entities.Rule;
import bespoke.entities.RunResult;
import bespoke.entities.RunSummary;
import bespoke.enums.Parameter;
import bespoke.jpa.AddressJpaRepo;
import bespoke.jpa.DisbursementSchemeConfigJpaRepo;
import bespoke.jpa.PersonJpaRepo;
import bespoke.rule.engine.DefaultRuleEngine;
import bespoke.rules.Rules;
import bespoke.rules.SimpleRule;
import bespoke.rules.poojo.RuleEngineSubject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RuleEngineService {
    @Autowired private KafkaProducer<String, byte[]> kafkaProducer;
    @Autowired private ObjectMapper objectMapper;

    @Autowired private DisbursementSchemeConfigJpaRepo disbursementSchemeConfigJpaRepo;
    @Autowired private PersonJpaRepo personJpaRepo;
    @Autowired private AddressJpaRepo addressJpaRepo;

    private int pageSize = 3;

    public void init() {
        try {
            kafkaProducer.sendMessage("topic.one", convert(InitRuleEngineDto.builder().build()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void trigger(InitRuleEngineDto dto) {
        DisbursementSchemeConfig config = this.disbursementSchemeConfigJpaRepo.findById(dto.getId()).orElse(null);

        if(Objects.isNull(config)) {
            System.out.println("No config found!");
            return;
        }

        List<Rule> rules = Collections.unmodifiableList(config.getRules());

        send("topic.two", RuleEngineDto.builder().config(config).rules(rules).build());
    }

    public void retrieve(RuleEngineDto dto) {
        if(Objects.isNull(dto) || Objects.isNull(dto.getRules()) || dto.getRules().isEmpty()) {
            System.out.println("RuleEngineDto got problem la!");
            return;
        }

        Rules<RuleEngineSubject, Map<Long, List<RunResult>>> rules = generateRuleEngineRules(dto.getRules());

        if(Objects.isNull(rules) || rules.isEmpty()) {
            System.out.println("No List<SimpleRule<RuleEngineSubject, List<RunResult>>> rules la!");
            return;
        }
        send("topic.three", RuleEngineTwoDto.builder().config(dto.getConfig()).rules(rules).build());
    }

    public void getCohort(RuleEngineTwoDto dto) {
        int size = personJpaRepo.countAll();
        int noOfPages = calculateNumberOfPages(size);

        for ( int i = 0 ; i < noOfPages ; i++ ) {
            send("topic.four", RuleEngineThreeDto.builder().config(dto.getConfig()).rules(dto.getRules()).pageIndex(i).pageSize(pageSize).build());
        }
    }

    public void getCohortPage(RuleEngineThreeDto dto) {
        List<Person> people = personJpaRepo.findByPageable(PageRequest.of(dto.getPageIndex(), dto.getPageSize())).orElse(null);
        if (Objects.isNull(people) || people.isEmpty() ) {
            System.out.println("JIALAT nobody to run on pageable "+dto.getPageIndex());
            return;
        }

        List<RuleEngineSubject> subjects = new ArrayList<>();
        for( int i = 0 ; i < people.size() ; i++ ) {
            Address address = addressJpaRepo.findByPerson(people.get(i)).orElse(null);
            subjects.add(RuleEngineSubject.builder().person(people.get(i)).address(address).build());
        }

        send("topic.five", RuleEngineFourDto.builder().config(dto.getConfig()).rules(dto.getRules()).subjects(subjects).build());
    }

    public void processCohortPage(RuleEngineFourDto dto) {
        DefaultRuleEngine<RuleEngineSubject, Map<Long, List<RunResult>>> ruleEngine = DefaultRuleEngine.<RuleEngineSubject, Map<Long, List<RunResult>>>builder().build();

        List<RuleEngineSubject> subjects = dto.getSubjects();
        Map<Long, List<RunResult>> runResults = new HashMap<>();
        for ( int i = 0 ; i < subjects.size() ; i++ ) {
            ruleEngine.trigger(dto.getRules(), subjects.get(i), runResults, runResults);
        }

        List<RunSummary> runSummaries = processRunResults(runResults);
    }

    private List<RunSummary> processRunResults(Map<Long, List<RunResult>> runResults) {
        List<RunSummary> summaries = new ArrayList<>();
        return summaries;
    }

    private Rules<RuleEngineSubject, Map<Long, List<RunResult>>> generateRuleEngineRules(List<Rule> rules) {
        Rules<RuleEngineSubject, Map<Long, List<RunResult>>> ruleEngineRules = new Rules<>();
        for ( int i = 0 ; i < rules.size() ; i++ ) {
            List<Criterion> criteria = rules.get(i).getCriteriaList();
            for ( int j = 0 ; j < criteria.size() ; j++ ) {
                List<Condition> conditions = criteria.get(j).getConditionList();
                for ( int k = 0 ; k < conditions.size() ; k++ ) {
                    //Based on the parameter
                    Parameter parameter = conditions.get(k).getParameter();

                    SimpleRule<RuleEngineSubject, Map<Long, List<RunResult>>> rule = new SimpleRule<>();

                    Long conditionId = conditions.get(k).getId();
                    String value = conditions.get(k).getValue();
                    rule = rule.name("")
                            .description("")
                            .when(parameter.create(conditions.get(k).getOperator(), conditions.get(k).getValue()))
                            .then((c, r) -> {
                                this.addPassAction(c,r,conditionId,value);
//                                if (r.containsKey(c.getPerson().getId())) {
//                                    r.get(c.getPerson().getId()).add(RunResult.builder().isPass(true).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
//                                } else {
//                                    r.put(c.getPerson().getId(), new ArrayList<>() {{
//                                        add(RunResult.builder().isPass(true).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
//                                    }});
//                                }
                            })
                            .orElse((c, r)->{
                                this.addFailAction(c,r,conditionId,value);
//                                if (r.containsKey(c.getPerson().getId())) {
//                                    r.get(c.getPerson().getId()).add(RunResult.builder().isPass(false).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
//                                } else {
//                                    r.put(c.getPerson().getId(), new ArrayList<>() {{
//                                        add(RunResult.builder().isPass(false).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
//                                    }});
//                                }
                            });

                    ruleEngineRules.addRule(rule);
                }
            }
        }
        return ruleEngineRules;
    }

    private void addPassAction(RuleEngineSubject c, Map<Long, List<RunResult>> r, Long conditionId, String value) {
        if (r.containsKey(c.getPerson().getId())) {
            r.get(c.getPerson().getId()).add(RunResult.builder().isPass(true).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
        } else {
            r.put(c.getPerson().getId(), new ArrayList<>() {{
                add(RunResult.builder().isPass(true).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
            }});
        }
    }

    private void addFailAction(RuleEngineSubject c, Map<Long, List<RunResult>> r, Long conditionId, String value) {
        if (r.containsKey(c.getPerson().getId())) {
            r.get(c.getPerson().getId()).add(RunResult.builder().isPass(false).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
        } else {
            r.put(c.getPerson().getId(), new ArrayList<>() {{
                add(RunResult.builder().isPass(false).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
            }});
        }
    }

    private void send(String topic, Object dto) {
        try {
            this.kafkaProducer.sendMessage(topic, convert(dto));
        } catch (JsonProcessingException e) {
            System.out.println("Cannot send la!");
        }
    }

    private byte[] convert(Object payload) throws JsonProcessingException {
        return this.objectMapper.writeValueAsBytes(payload);
    }

    private   int calculateNumberOfPages(int cohortSize) {
        return ((cohortSize - 1) / pageSize) + 1;
    }
}