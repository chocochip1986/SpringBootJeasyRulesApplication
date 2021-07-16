package bespoke.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "criterion")
public class Criterion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "criterion_generator")
    private Long id;
    private int sequence;
    @OneToOne
    @JoinColumn(name = "rule_id", referencedColumnName = "id", nullable = false)
    private Rule rule;
    private String description;
    private boolean deleted;
    @JsonIgnore
    @OneToMany(mappedBy = "criterion", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Condition> conditionList;

    public String toString() {
        return "";
    }
}
