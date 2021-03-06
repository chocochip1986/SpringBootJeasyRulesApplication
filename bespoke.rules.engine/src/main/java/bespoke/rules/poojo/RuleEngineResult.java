package bespoke.rules.poojo;

import bespoke.entities.Condition;
import bespoke.entities.RunResult;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class RuleEngineResult {
    private List<RunResult> runResults;
    //Map of {"ruleId" -> { Map of "criterionId" -> { list of booleans } }}
    private Map<Long, Map<Long, List<Boolean>>> resultMatrix;

    public RuleEngineResult() {
        this.runResults = new ArrayList<>();
        this.resultMatrix = new HashMap<>();
    }

    public void updateRunResultsWithPass(RuleEngineSubject c, Long conditionId, String value) {
        this.runResults.add(RunResult.builder().isPass(true).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
    }

    public void updateRunResultsWithFail(RuleEngineSubject c, Long conditionId, String value) {
        this.runResults.add(RunResult.builder().isPass(false).personId(c.getPerson().getId()).conditionId(conditionId).actualValue(value).build());
    }

    public void updateResultMatrix(Long ruleId, Long criterionId, Long conditionId, boolean pass) {
        if (this.resultMatrix.containsKey(ruleId)) {
            //Add to existing ruleId
            Map<Long, List<Boolean>> criteria = this.resultMatrix.get(ruleId);
            if (criteria.containsKey(criterionId)) {
                criteria.get(criterionId).add(pass);
            } else {
                criteria.put(criterionId, new ArrayList<>());
            }
        } else {
            //Add new ruleId with Map of condition Id to list of conditions
            this.resultMatrix.put(ruleId, new HashMap<>() {{
                put(criterionId, new ArrayList<>() {{
                    add(pass);
                }});
            }});
        }
    }

    public boolean deriveNetPassOrFail() {
        List<List<Boolean>> results = new ArrayList<>();
        this.resultMatrix.forEach((ruleId, vCriterionMap) -> {
            results.add(new ArrayList<>());
            vCriterionMap.forEach((criterionId, listOfBooleans) -> {
                results.get(results.size()-1).add(listOfBooleans.stream().reduce(true, (a,b) -> a && b));
            });
        });

        return results.stream()
                .map(rule -> rule.stream()
                        .reduce(false, (a,b) -> a || b))
                .reduce(true, (a,b) -> a && b);
    }
}
