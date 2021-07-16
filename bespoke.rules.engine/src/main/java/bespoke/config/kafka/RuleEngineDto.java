package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.Rule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleEngineDto {
    private DisbursementSchemeConfig config;
    private List<Rule> rules;
}
