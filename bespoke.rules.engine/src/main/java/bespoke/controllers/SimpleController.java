package bespoke.controllers;

import bespoke.fake.Fake;
import bespoke.services.RuleEngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @Autowired private RuleEngineService ruleEngineService;
    @Autowired private Fake fake;
    @GetMapping()
    public ResponseEntity<String> trigger() {
        ruleEngineService.init();
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(value = "/hi")
    public ResponseEntity<String> triggerFake() {
        fake.create();
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
