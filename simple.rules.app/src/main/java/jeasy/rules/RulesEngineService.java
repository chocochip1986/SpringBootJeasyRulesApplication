package jeasy.rules;


import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RulesEngineService {
    private ExecutorService executorService;

    public RulesEngineService() {
        this.executorService = Executors.newFixedThreadPool(16);
    }

    public void trigger(Rules rules, Facts facts) {
        RulesEngineTask task = new RulesEngineTask(new DefaultRulesEngine(), rules, facts);
        this.executorService.execute(task);
    }
}
