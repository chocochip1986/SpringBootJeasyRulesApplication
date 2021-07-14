package bespoke.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class DisbursementSchemeConfig {
    @Id
    private Long id;

    @OneToMany(mappedBy = "disbursementSchemeConfig", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rule> rules;
}
