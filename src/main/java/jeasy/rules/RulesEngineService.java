package jeasy.rules;


import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

public class RulesEngineService {
    private RulesEngine rulesEngine;

    public RulesEngineService() {
        this.rulesEngine = new DefaultRulesEngine();
    }

    public void trigger(Rules rules, Facts facts) {
        this.rulesEngine.fire(rules, facts);
    }
}
