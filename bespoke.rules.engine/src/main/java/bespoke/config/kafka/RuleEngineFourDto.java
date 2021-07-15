package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.rules.Rules;
import bespoke.rules.poojo.RuleEngineResult;
import bespoke.rules.poojo.RuleEngineSubject;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class RuleEngineFourDto {
    private DisbursementSchemeConfig config;
    private Rules<RuleEngineSubject, Map<Long, RuleEngineResult>> rules;
    private List<RuleEngineSubject> subjects;
}
