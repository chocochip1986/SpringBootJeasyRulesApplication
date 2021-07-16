package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.rules.Rules;
import bespoke.rules.poojo.RuleEngineResult;
import bespoke.rules.poojo.RuleEngineSubject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class RuleEngineTwoDto {
    private DisbursementSchemeConfig config;
    private Rules<RuleEngineSubject, Map<Long, RuleEngineResult>> rules;

    public RuleEngineTwoDto() {
        this.config = null;
        this.rules = new Rules<>();
    }
}
