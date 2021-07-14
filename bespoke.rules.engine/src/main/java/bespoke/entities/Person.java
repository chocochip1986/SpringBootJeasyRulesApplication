package bespoke.entities;

import bespoke.enums.Nationality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Person implements Evaluatable {
    @Id
    private Long id;
    private String name;
    private String nric;
    @Enumerated(EnumType.STRING)
    private Nationality nationality;
    private LocalDateTime birthDate;
    private LocalDateTime deathDate;
    private LocalDateTime citizenshipAttainmentDate;
    private Double income;

    @OneToMany(mappedBy = "person")
    private List<Address> addresses;

    @Override
    public boolean methodA() {
        return true;
    }
}
