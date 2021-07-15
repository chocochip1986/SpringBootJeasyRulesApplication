package bespoke.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class RunSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "run_summary_generator")
    private Long id;
    private Long personId;
    private boolean pass;
}
