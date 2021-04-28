package jeasy.multi.threaded.services;

import commons.entities.Person;
import jeasy.multi.threaded.services.steps.retrieve.cohort.RetrieveCohort;
import jeasy.multi.threaded.services.steps.rules.GenerateRules;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.BasicRule;
import org.jeasy.rules.mvel.MVELRule;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class DefaultRuleEngineConfigurer {
    private RetrieveCohort<String, List<Person>> retriever;
    private GenerateRules<Person, Map<Person, List<Rule>>> eligibilityRulesGenerator;
    private final int limitPerPage = 300;

    public static Builder builder() {
        return new Builder();
    }

    public DefaultRuleEngineConfigurer(Builder builder) {
        this.retriever = builder.retriever;
        this.eligibilityRulesGenerator = builder.eligibilityRulesGenerator;
    }

    public Predicate<String> configureARuleEngine() {
        return (key) -> {
            try {
                if( key == null || key.isEmpty() ) {
                    return false;
                }
                // Retrieving cohort
                // List<Person> cohort = ...
                List<Person> cohort = this.retriever.retrieveCohort(key);
                if ( cohort == null || cohort.size() < 0 ) {
                    return false;
                }

                // Retrieve Eligibility Rules and convert to Rule
                // Retrieve Exclusion Rules and convert to Rule
                Map<Person, List<Rule>> rulesAndFacts = this.eligibilityRulesGenerator.generateRules(cohort);

                // function
                // Post rule engine actions


                return true;
            } catch (RuntimeException e) {
                return false;
            }
            // Find cohort to run on
            // If cohort is zero, return true;
            // Find rules
            // Generate MVERules
            // Generate Facts
            // Run rules on cohort
            // Calculate how to split the task into multiple tasks -- optional
            // Collate results back -- optional
        };
    }
    public static class Builder {
        private List<Rule> eligibilityRules;
        private List<Rule> exclusionRules;
        private RetrieveCohort<String, List<Person>> retriever;
        private GenerateRules<Person, Map<Person, List<Rule>>> eligibilityRulesGenerator;
        private String runKeyId;

        public Builder() {
            eligibilityRules = Collections.emptyList();
            exclusionRules = Collections.emptyList();
        }

        public Builder setEligibilityRules(List<Rule> rules) {
            this.eligibilityRules = rules;
            return this;
        }

        public Builder setExclusionRules(List<Rule> rules) {
            this.exclusionRules = rules;
            return this;
        }

        public Builder setEligibilityRulesGenerator(GenerateRules<Person, Map<Person, List<Rule>>> generator) {
            this.eligibilityRulesGenerator = generator;
            return this;
        }

        public Builder setRunKey(String key) {
            this.runKeyId = key;
            return this;
        }

        public Builder setCohortRetriever(RetrieveCohort<String, List<Person>> r) {
            this.retriever = r;
            return this;
        }

        public DefaultRuleEngineConfigurer build() {
            validate();
            return new DefaultRuleEngineConfigurer(this);
        }

        private void validate() {
            if( this.runKeyId == null ||
                    this.eligibilityRules == null ||
                    this.exclusionRules == null ||
                    this.retriever == null ) {
                throw new RuntimeException("HELLO, CANNOT LA");
            }
        }
    }
}
