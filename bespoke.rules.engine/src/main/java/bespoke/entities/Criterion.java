package bespoke.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Criterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "criterion_generator")
    private Long id;
    private int sequence;
    @OneToOne
    private Rule rule;
    private String description;
    private boolean deleted;
    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Condition> conditionList;
}
