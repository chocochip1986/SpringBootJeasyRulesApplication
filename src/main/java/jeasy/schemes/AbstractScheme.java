package jeasy.schemes;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;

public class AbstractScheme {
    protected Rules rules;
    protected String name;
    protected String description;

    public void addRule(Rule rule) {
        this.rules.register(rules);
    }

    public void addRules(Rule... rules) {
        this.rules.register(rules);
    }

    public Rules retrieveRules() {
        return this.rules;
    }
}
