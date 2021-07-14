package bespoke.jpa;

import bespoke.entities.Address;
import bespoke.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressJpaRepo extends JpaRepository<Address, Long> {
    Optional<Address> findByPerson(Person p);
}
