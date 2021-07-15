package bespoke.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RunResult {
    @Id
    private String id;
    private Long personId;
    private String ruleName;
    private Long conditionId;
    private boolean isPass;
    private String remark;
    private String actualValue;
}
