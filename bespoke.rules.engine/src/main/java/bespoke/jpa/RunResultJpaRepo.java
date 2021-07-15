package bespoke.jpa;

import bespoke.entities.RunResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunResultJpaRepo extends JpaRepository<RunResult, Long> {
}
