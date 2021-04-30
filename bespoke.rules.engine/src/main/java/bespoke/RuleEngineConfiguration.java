package bespoke;

import bespoke.entities.Animal;
import bespoke.entities.RunResult;
import bespoke.rule.engine.DefaultRuleEngine;
import bespoke.rule.engine.RuleEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RuleEngineConfiguration {

    @Bean
    public RuleEngine<Animal> ruleEngine() {
        return DefaultRuleEngine.<Animal>builder()
                .setPostProcessor(() -> {
                    RunResult.builder().ruleName("").build();
                })
                .build();
    }
}
