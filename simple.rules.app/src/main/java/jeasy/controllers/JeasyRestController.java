package jeasy.controllers;

import commons.entities.Person;
import commons.enums.Gender;
import commons.enums.Nationality;
import jeasy.rules.RulesEngineService;
import jeasy.schemes.TrueBlueSingaporeanScheme;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JeasyRestController {
    @GetMapping(value = "/api/v1/trigger")
    public ResponseEntity<String> triggerRules() {
        RulesEngineService service = new RulesEngineService();
        for ( int i = 0 ; i < 50 ; i++ ) {
            Person person = Person.createRandom();
            System.out.println("Person = " + person.toString());

            Facts facts = buildFactsForPerson(person);
            service.trigger(TrueBlueSingaporeanScheme.builder().build().retrieveRules(), facts);
        }
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    private Facts buildFactsForPerson(Person person) {
        Facts facts = new Facts();
        Fact<Boolean> hasOLevelFact = new Fact<>("tookOLevels", person.isTookOLevels());
        Fact<Integer> oLevelScore = new Fact<>("oLevelScore", person.getOLevelScore());
        Fact<String> nameOfPerson = new Fact<>("name", person.getName());
        Fact<Gender> genderOfPerson = new Fact<>("gender", person.getGender());
        Fact<Nationality> nationalityOfPerson = new Fact<>("nationality", person.getNationality());

        facts.add(nameOfPerson);
        facts.add(genderOfPerson);
        facts.add(nationalityOfPerson);
        facts.add(hasOLevelFact);
        facts.add(oLevelScore);
        return facts;
    }
}
