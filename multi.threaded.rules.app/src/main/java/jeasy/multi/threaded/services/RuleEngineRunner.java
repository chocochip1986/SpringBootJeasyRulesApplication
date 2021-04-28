package jeasy.multi.threaded.services;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleEngineRunner {

    @Autowired
    private RulesBuilder builder;

    public RulesEngine createNewRuleEngine() {
        return new DefaultRulesEngine();
    }

    public Runnable createRuleEngineRunnable() {
        return () -> {
        };
    }

    public void trigger(Rules rules, Facts facts) {
        //Initialize section

        RulesEngine engine = createNewRuleEngine();
        engine.fire(rules, facts);
    }
}
