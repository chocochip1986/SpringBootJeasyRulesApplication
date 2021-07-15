package bespoke.services;

import bespoke.config.kafka.KafkaProducer;
import bespoke.entities.Condition;
import bespoke.entities.Criterion;
import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.Rule;
import bespoke.enums.Operator;
import bespoke.enums.Parameter;
import bespoke.jpa.AddressJpaRepo;
import bespoke.jpa.DisbursementSchemeConfigJpaRepo;
import bespoke.jpa.PersonJpaRepo;
import bespoke.jpa.RunResultJpaRepo;
import bespoke.jpa.RunSummaryJpaRepo;
import bespoke.rules.Rules;
import bespoke.rules.poojo.RuleEngineResult;
import bespoke.rules.poojo.RuleEngineSubject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RuleEngineServiceTest {
    @Mock private KafkaProducer<String, byte[]> kafkaProducer;
    @Mock private ObjectMapper objectMapper;

    @Mock private DisbursementSchemeConfigJpaRepo disbursementSchemeConfigJpaRepo;
    @Mock private PersonJpaRepo personJpaRepo;
    @Mock private AddressJpaRepo addressJpaRepo;
    @Mock private RunSummaryJpaRepo runSummaryJpaRepo;
    @Mock private RunResultJpaRepo runResultJpaRepo;
    @InjectMocks private RuleEngineService service;

    @BeforeEach
    public void setUp() {}

    @Test
    public void generateRuleEngineRules_1() {
        List<Rule> rules = createSomeRules();

        Rules<RuleEngineSubject, Map<Long, RuleEngineResult>> ruleEngineRules =  service.generateRuleEngineRules(rules);
    }

    private List<Rule> createSomeRules() {
        DisbursementSchemeConfig config = DisbursementSchemeConfig.builder().id(1L).build();

        return new ArrayList<>() {{
            add(Rule.builder()
                    .id(1L)
                    .disbursementSchemeConfig(config)
                    .deleted(false)
                    .description("Rule 1")
                    .criteriaList(new ArrayList<>() {{
                        add(Criterion.builder().id(1L).conditionList(new ArrayList<>() {{
                            add(Condition.builder().id(1L).parameter(Parameter.NATIONALITY).operator(Operator.EQUAL_TO).value("SINGAPOREAN").build());
                        }}).build());
                    }})
                    .build());
            add(Rule.builder()
                    .id(2L)
                    .disbursementSchemeConfig(config)
                    .deleted(false)
                    .description("Rule 2")
                    .criteriaList(new ArrayList<>() {{
                        add(Criterion.builder().id(1L).conditionList(new ArrayList<>() {{
                            add(Condition.builder().id(1L).parameter(Parameter.LIVING_STATUS).operator(Operator.EQUAL_TO).value("ALIVE").build());
                        }}).build());
                    }})
                    .build());
        }};
    }

}