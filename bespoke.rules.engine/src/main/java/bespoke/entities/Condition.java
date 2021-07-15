package bespoke.entities;

import bespoke.enums.Operator;
import bespoke.enums.Parameter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "condition_generator")
    private Long id;
    private int sequence;
    @OneToOne
    private Criterion criterion;
    @Enumerated(EnumType.STRING)
    private Parameter parameter;
    @Enumerated(EnumType.STRING)
    private Operator operator;
    private String value;
    private LocalDateTime asOf;
    private Boolean pass;
    private Boolean deleted;
}
