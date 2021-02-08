package jeasy.controllers;

import jeasy.entities.Person;
import jeasy.rules.RulesEngineService;
import org.jeasy.rules.api.Fact;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.RuleBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JeasyRestController {
    @GetMapping(value = "/api/v1/trigger")
    public ResponseEntity<String> triggerRules() {
        Person person = Person.createRandom();
        System.out.println("Person = " + person.toString());

        Facts facts = buildFactsForPerson(person);

        Rule psleRule = new RuleBuilder()
                .name("Must pass PSLE Rule")
                .description("If person passes PSLE, then ok")
                .when((facts1) -> facts1.get("PSLE Fact").equals(true))
                .then((facts1 -> System.out.println(facts1.get("name")+ " passed PSLE")))
                .build();
        Rule oLevelRule = new RuleBuilder()
                .name("Must pass O Levels Rule")
                .description("If person passes PSLE, then ok")
                .when((facts1) -> facts1.get("OLevel Fact").equals(true))
                .then((facts1 -> System.out.println(facts1.get("name")+ " passed O Levels")))
                .build();
        Rules rules = new Rules();

        rules.register(oLevelRule);
        rules.register(psleRule);


        RulesEngineService service = new RulesEngineService();
        service.trigger(rules, facts);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    private Facts buildFactsForPerson(Person person) {
        Facts facts = new Facts();
        Fact<Boolean> psleFact = new Fact<>("PSLE Fact", person.getPsleScore() > 250 );
        Fact<Boolean> oLevelFact = new Fact<>("OLevel Fact", person.isTookOLevels() && person.getOLevelScore() < 16);
        Fact<String> nameOfPerson = new Fact<>("name", person.getName());

        facts.add(psleFact);
        facts.add(oLevelFact);
        return facts;
    }
}
