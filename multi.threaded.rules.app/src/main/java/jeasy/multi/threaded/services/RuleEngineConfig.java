package jeasy.multi.threaded.services;

import commons.entities.Person;
import jeasy.multi.threaded.services.steps.retrieve.cohort.RetrieveCohort;
import jeasy.multi.threaded.services.steps.rules.GenerateRules;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class RuleEngineConfig {
    //Autowire all the necessary repos here and
    @Bean
    public RetrieveCohort<String, List<Person>> cohortRetriever() {
        return (key) -> Collections.emptyList();
    }

    @Bean
    public GenerateRules<Person, Map<Person, List<Person>>> generateEligibilityRules() {
        return (persons) -> Collections.EMPTY_MAP;
    }

    @Bean
    public DefaultRuleEngineConfigurer defaultRuleEngineConfigurer() {
        return DefaultRuleEngineConfigurer.builder()
                .setEligibilityRules(Collections.emptyList())
                .setExclusionRules(Collections.emptyList())
                .setCohortRetriever(cohortRetriever())
                .build();
    }
}
