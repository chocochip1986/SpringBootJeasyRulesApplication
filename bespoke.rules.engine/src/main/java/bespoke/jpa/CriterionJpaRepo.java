package bespoke.jpa;

import bespoke.entities.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionJpaRepo extends JpaRepository<Criterion, Long> {}
