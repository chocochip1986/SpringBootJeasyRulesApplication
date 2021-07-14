package bespoke;


import bespoke.entities.Condition;
import bespoke.entities.Criterion;
import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.Rule;
import bespoke.entities.RunResult;
import bespoke.enums.Parameter;
import bespoke.rules.SimpleRule;
import bespoke.rules.poojo.RuleEngineSubject;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public void retrieveRules() {
        DisbursementSchemeConfig config = new DisbursementSchemeConfig();
        List<Rule> rules = config.getRules();

        List<SimpleRule<RuleEngineSubject, List<RunResult>>> ruleEngineRules = new ArrayList<>();
        for ( int i = 0 ; i < rules.size() ; i++ ) {
            List<Criterion> criteria = rules.get(i).getCriteriaList();
            for ( int j = 0 ; j < criteria.size() ; j++ ) {
                List<Condition> conditions = criteria.get(j).getConditionList();
                for ( int k = 0 ; k < conditions.size() ; k++ ) {
                    //Based on the parameter
                    Parameter parameter = conditions.get(k).getParameter();

                    SimpleRule<RuleEngineSubject, List<RunResult>> rule = new SimpleRule<>();

                    Long conditionId = conditions.get(k).getId();
                    String value = conditions.get(k).getValue();
                    rule = rule.name("")
                            .description("")
                            .when(parameter.create(conditions.get(k).getOperator(), conditions.get(k).getValue()))
                            .then((r) -> {
                                r.add(RunResult.builder().isPass(true).conditionId(conditionId).actualValue(value).build());
                            })
                            .orElse((r)->{
                                r.add(RunResult.builder().isPass(false).conditionId(conditionId).actualValue(value).build());
                            });

                    ruleEngineRules.add(rule);
                }
            }
        }
    }


}
