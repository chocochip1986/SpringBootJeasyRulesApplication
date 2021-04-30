package bespoke.rule.engine;

import bespoke.rules.Rule;
import bespoke.rules.Rules;

import java.util.Iterator;

public class DefaultRuleEngine<C> implements RuleEngine<C> {
    private RulePassPostProcessor postProcessor;
    public static <C> Builder<C> builder() {
        return new Builder<>();
    }

    public DefaultRuleEngine(Builder<C> builder) {
        this.postProcessor = builder.postProcessor;
    }
    @Override
    public void trigger(Rules<C> rules, C c) {
        if (rules.isEmpty()) {
            System.out.println("No rules so nothing ran...");
        } else {
            Iterator<Rule<C>> itr = rules.getIterator();
            while(itr.hasNext()) {
                Rule<C> rule = itr.next();
                if ( rule.evaluate(c) ) {
                    int size = rule.getActions().size();
                    for( int i = 0 ; i < size ; i++ ) {
                        rule.getActions().get(i).execute();
                    }
                    //DO POST PROCESSING
                    postProcessor.execute();
                } else {
                    System.out.println("Rule ["+rule.getRuleName()+"] failed...");
                }
            }
        }
    }

    public static class Builder<C> {
        private RulePassPostProcessor postProcessor;
        public Builder() {
            this.postProcessor = null;
        }

        public Builder<C> setPostProcessor(RulePassPostProcessor p) {
            this.postProcessor = p;
            return this;
        }

        public DefaultRuleEngine<C> build(){
            return new DefaultRuleEngine<>(this);
        }
    }
}
