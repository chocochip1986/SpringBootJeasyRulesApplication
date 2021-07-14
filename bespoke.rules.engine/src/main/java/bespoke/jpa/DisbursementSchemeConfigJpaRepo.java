package bespoke.jpa;

import bespoke.entities.DisbursementSchemeConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisbursementSchemeConfigJpaRepo extends JpaRepository<DisbursementSchemeConfig, Long> {
}
