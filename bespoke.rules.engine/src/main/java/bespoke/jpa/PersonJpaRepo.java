package bespoke.jpa;

import bespoke.entities.Person;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonJpaRepo extends JpaRepository<Person, Long> {
    int countAll();

    @Query(value = "SELECT p FROM Person p ORDER BY c.id DESC")
    Optional<List<Person>> findByPageable(Pageable pageable);
}
