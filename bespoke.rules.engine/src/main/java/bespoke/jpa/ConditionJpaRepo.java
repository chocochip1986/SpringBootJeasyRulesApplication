package bespoke.jpa;

import bespoke.entities.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionJpaRepo extends JpaRepository<Condition, Long> {}
