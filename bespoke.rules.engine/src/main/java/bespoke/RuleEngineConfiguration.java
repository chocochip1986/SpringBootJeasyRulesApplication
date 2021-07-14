package bespoke;

import bespoke.entities.RunResult;
import bespoke.rule.engine.DefaultRuleEngine;
import bespoke.rule.engine.RuleEngine;
import bespoke.rules.poojo.RuleEngineSubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleEngineConfiguration {

    @Bean
    public RuleEngine<RuleEngineSubject, RunResult> ruleEngine() {
        return DefaultRuleEngine.<RuleEngineSubject, RunResult>builder()
                .setPostProcessor(() -> {})
                .build();
    }
}
