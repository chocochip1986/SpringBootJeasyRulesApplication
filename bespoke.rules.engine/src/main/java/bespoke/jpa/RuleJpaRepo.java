package bespoke.jpa;

import bespoke.entities.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleJpaRepo extends JpaRepository<Rule, Long> {}
