package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.RunResult;
import bespoke.rules.Rules;
import bespoke.rules.SimpleRule;
import bespoke.rules.poojo.RuleEngineSubject;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RuleEngineTwoDto {
    private DisbursementSchemeConfig config;
    private Rules<RuleEngineSubject, List<RunResult>> rules;
}
