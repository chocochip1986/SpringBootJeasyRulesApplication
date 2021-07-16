package bespoke.config.kafka;

import bespoke.entities.DisbursementSchemeConfig;
import bespoke.entities.RunResult;
import bespoke.entities.RunSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleEngineFiveDto {
    private DisbursementSchemeConfig config;
    private List<RunSummary> runSummaries;
    private List<RunResult> runResults;
}
