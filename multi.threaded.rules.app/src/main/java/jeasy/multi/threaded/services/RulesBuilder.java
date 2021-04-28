package jeasy.multi.threaded.services;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRule;
import org.springframework.stereotype.Service;

@Service
public class RulesBuilder {
    public Rules buildRules() {
        return new Rules();
    }

    public Rule generateRule() {
        return new MVELRule().description("").name("").priority(1).when("").then("");
    }

}
