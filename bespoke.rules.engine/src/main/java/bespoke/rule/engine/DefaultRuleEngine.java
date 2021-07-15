package bespoke.rule.engine;

import bespoke.rules.Rule;
import bespoke.rules.Rules;

import java.util.Iterator;

public class DefaultRuleEngine<C,R> implements RuleEngine<C,R> {
    private RuleEngineProcessor<C> postRuleProcessor;
    private RuleEngineProcessor<C> preRuleProcessor;
    private RuleEngineProcessor<C> postRulesProcessor;
    private boolean allPassed = true;
    public static <C,R> Builder<C,R> builder() {
        return new Builder<>();
    }

    public DefaultRuleEngine(Builder<C,R> builder) {
        this.postRuleProcessor = builder.postRuleProcessor;
        this.preRuleProcessor = builder.preRuleProcessor;
        this.postRulesProcessor = builder.postRulesProcessor;
    }

    @Override
    public void trigger(Rules<C,R> rules, C c, R r1, R r2) {
        try {
            if (rules.isEmpty()) {
                System.out.println("No rules so nothing ran...");
            } else {
                Iterator<Rule<C,R>> itr = rules.getIterator();
                while(itr.hasNext()) {
                    Rule<C,R> rule = itr.next();
                    this.preRuleProcessor.execute(c);
                    if ( rule.evaluate(c) ) {
                        //Passed rule
                        int size = rule.getActions().size();
                        for( int i = 0 ; i < size ; i++ ) {
                            rule.getActions().get(i).execute(c, r1);
                        }
                    } else {
                        //Failed rule
                        System.out.println("Rule ["+rule.getRuleName()+"] failed...");
                        allPassed = false;
                        int size = rule.getFailureActions().size();
                        for( int i = 0 ; i < size ; i++ ) {
                            rule.getFailureActions().get(i).execute(c, r2);
                        }
                    }
                    postRuleProcessor.execute(c);
                }

                performAfterRulesEvaluatedProcessing(c);
            }
        } catch (Exception e) {
            System.out.println("Rule Engine Died");
        }
    }

    public static class Builder<C,R> {
        private RuleEngineProcessor<C> postRuleProcessor;
        private RuleEngineProcessor<C> preRuleProcessor;
        private RuleEngineProcessor<C> postRulesProcessor;
        public Builder() {
            this.postRuleProcessor = (c)->{};
            this.preRuleProcessor = (c)->{};
            this.postRulesProcessor = (c)->{};
        }

        public Builder<C,R> setPostRuleProcessor(RuleEngineProcessor<C> p) {
            this.postRuleProcessor = p;
            return this;
        }

        public Builder<C,R> setPostRulesProcessor(RuleEngineProcessor<C> p) {
            this.postRulesProcessor = p;
            return this;
        }

        public Builder<C,R> setPreRuleProcessor(RuleEngineProcessor<C> p) {
            this.preRuleProcessor = p;
            return this;
        }

        public DefaultRuleEngine<C,R> build(){
            return new DefaultRuleEngine<>(this);
        }
    }

    private void performAfterRulesEvaluatedProcessing(C c) {
        this.postRulesProcessor.execute(c);
        if (allPassed) {

        } else {

        }
    }
}
