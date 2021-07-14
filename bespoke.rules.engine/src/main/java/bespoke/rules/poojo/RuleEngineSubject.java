package bespoke.rules.poojo;

import bespoke.entities.Address;
import bespoke.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RuleEngineSubject {
    private Person person;
    private Address address;
}
