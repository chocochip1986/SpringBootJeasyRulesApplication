package bespoke.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "run_result")
public class RunResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "run_result_generator")
    private String id;
    private Long personId;
    private String ruleName;
    private Long conditionId;
    private boolean isPass;
    private String remark;
    private String actualValue;
}
