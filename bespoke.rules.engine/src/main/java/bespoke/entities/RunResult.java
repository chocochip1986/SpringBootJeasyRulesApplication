package bespoke.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RunResult {
    private String id;
    private String ruleName;
    private Long conditionId;
    private boolean isPass;
    private String remark;
    private String actualValue;
}
