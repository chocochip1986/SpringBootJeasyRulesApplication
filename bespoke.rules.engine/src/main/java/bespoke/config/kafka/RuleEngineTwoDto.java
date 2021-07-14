package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.RunResult;
import bespoke.rules.Rules;
import bespoke.rules.poojo.RuleEngineSubject;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class RuleEngineTwoDto {
    private DisbursementSchemeConfig config;
    private Rules<RuleEngineSubject, Map<Long, List<RunResult>>> rules;
}