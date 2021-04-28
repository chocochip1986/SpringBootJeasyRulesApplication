package jeasy.multi.threaded.controllers;

import jeasy.multi.threaded.services.RuleEngineRunner;
import jeasy.multi.threaded.services.RulesBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class SimpleRestController {
    @Autowired
    private RuleEngineRunner ruleEngineRunner;

    @Autowired
    private RulesBuilder rulesBuilder;
    @GetMapping(value = "/api/v1/trigger")
    public ResponseEntity<String> triggerRules() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(() -> {
            ruleEngineRunner.createRuleEngineRunnable();
            //Retrieve necessary data to build rules
//            ruleEngineRunner.trigger();
        });
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
