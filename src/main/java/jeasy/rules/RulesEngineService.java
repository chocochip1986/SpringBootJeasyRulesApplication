package jeasy.rules;


import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RulesEngineService {
    private ExecutorService executorService;

    public RulesEngineService() {
        this.executorService = Executors.newFixedThreadPool(16);
    }

    public void trigger(Rules rules, Facts facts) {
        this.executorService.execute(() -> {
            try {
                System.out.println("Thread: "+ Thread.currentThread().getId()+" Time: "+System.currentTimeMillis());
                RulesEngine rulesEngine = new DefaultRulesEngine();
                rulesEngine.fire(rules, facts);
            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
