package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.Rule;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class RuleEngineDto {
    private DisbursementSchemeConfig config;
    private List<Rule> rules;
}
