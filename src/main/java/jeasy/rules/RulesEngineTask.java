package jeasy.rules;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;

public class RulesEngineTask implements Runnable {
    private RulesEngine rulesEngine;
    private Rules rules;
    private Facts facts;

    public RulesEngineTask(RulesEngine rulesEngine, Rules rules, Facts facts) {
        this.rulesEngine = rulesEngine;
        this.rules = rules;
        this.facts = facts;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread: "+ Thread.currentThread().getId()+" Time: "+System.currentTimeMillis());
            rulesEngine.fire(rules, facts);
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
