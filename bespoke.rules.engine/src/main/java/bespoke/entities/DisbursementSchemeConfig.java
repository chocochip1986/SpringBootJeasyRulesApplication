package bespoke.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "disbursement_scheme_config")
public class DisbursementSchemeConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "config_generator")
    private Long id;

    @OneToMany(mappedBy = "disbursementSchemeConfig", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rule> rules;
}
