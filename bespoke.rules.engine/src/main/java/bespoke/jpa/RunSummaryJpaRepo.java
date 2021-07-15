package bespoke.jpa;

import bespoke.entities.RunSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RunSummaryJpaRepo extends JpaRepository<RunSummary, Long> {
}
