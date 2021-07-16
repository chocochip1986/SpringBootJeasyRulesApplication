package bespoke.fake;

import bespoke.entities.Person;
import bespoke.enums.Nationality;
import bespoke.jpa.ConditionJpaRepo;
import bespoke.jpa.CriterionJpaRepo;
import bespoke.jpa.DisbursementSchemeConfigJpaRepo;
import bespoke.jpa.PersonJpaRepo;
import bespoke.jpa.RuleJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Fake {
    @Autowired private DisbursementSchemeConfigJpaRepo disbursementSchemeConfigJpaRepo;
    @Autowired private RuleJpaRepo ruleJpaRepo;
    @Autowired private CriterionJpaRepo criterionJpaRepo;
    @Autowired private ConditionJpaRepo conditionJpaRepo;
    @Autowired private PersonJpaRepo personJpaRepo;
    public void create() {
        personJpaRepo.save(Person.builder().name("001").nric("S1A").income(10.2).birthDate(LocalDateTime.of(1986, 12, 1, 0 ,0)).nationality(Nationality.NON_SINGAPOREAN).build());
        personJpaRepo.save(Person.builder().name("002").nric("S2A").income(321.1).birthDate(LocalDateTime.of(2000, 1, 5, 0 ,0)).nationality(Nationality.PR).build());
        personJpaRepo.save(Person.builder().name("003").nric("S3A").income(3232.20).birthDate(LocalDateTime.of(2001, 6, 15, 0 ,0)).nationality(Nationality.SINGAPOREAN).build());
        personJpaRepo.save(Person.builder().name("004").nric("S4A").income(5303.0).birthDate(LocalDateTime.of(2002, 7, 20, 0 ,0)).nationality(Nationality.DUAL_CITIZEN).build());
        personJpaRepo.save(Person.builder().name("005").nric("S5A").income(600.20).birthDate(LocalDateTime.of(2003, 8, 25, 0 ,0)).nationality(Nationality.NON_SINGAPOREAN).build());
        personJpaRepo.save(Person.builder().name("006").nric("S6A").income(1231.0).birthDate(LocalDateTime.of(2004, 9, 30, 0 ,0)).nationality(Nationality.PR).build());
        personJpaRepo.save(Person.builder().name("007").nric("S7A").income(50.230).birthDate(LocalDateTime.of(2005, 10, 10, 0 ,0)).nationality(Nationality.SINGAPOREAN).build());
        personJpaRepo.save(Person.builder().name("008").nric("S8A").income(23.10).birthDate(LocalDateTime.of(2006, 11, 15, 0 ,0)).nationality(Nationality.DUAL_CITIZEN).build());
        personJpaRepo.save(Person.builder().name("009").nric("S9A").income(43430.0).birthDate(LocalDateTime.of(2007, 2, 20, 0 ,0)).nationality(Nationality.NON_SINGAPOREAN).build());
        personJpaRepo.save(Person.builder().name("010").nric("S0A").income(12110.0).birthDate(LocalDateTime.of(2008, 3, 5, 0 ,0)).nationality(Nationality.PR).build());
    }
}
