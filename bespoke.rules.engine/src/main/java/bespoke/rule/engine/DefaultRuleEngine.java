package bespoke.rule.engine;

import bespoke.rules.Rule;
import bespoke.rules.Rules;

import java.util.Iterator;

public class DefaultRuleEngine<C,R> implements RuleEngine<C,R> {
    private RulePassPostProcessor postProcessor;
    public static <C,R> Builder<C,R> builder() {
        return new Builder<>();
    }

    public DefaultRuleEngine(Builder<C,R> builder) {
        this.postProcessor = builder.postProcessor;
    }
    @Override
    public void trigger(Rules<C,R> rules, C c, R r) {
        if (rules.isEmpty()) {
            System.out.println("No rules so nothing ran...");
        } else {
            Iterator<Rule<C,R>> itr = rules.getIterator();
            while(itr.hasNext()) {
                Rule<C,R> rule = itr.next();
                if ( rule.evaluate(c) ) {
                    int size = rule.getActions().size();
                    for( int i = 0 ; i < size ; i++ ) {
                        rule.getActions().get(i).execute(r);
                    }
                    //DO POST PROCESSING
                    postProcessor.execute();
                } else {
                    System.out.println("Rule ["+rule.getRuleName()+"] failed...");
                }
            }
        }
    }

    public static class Builder<C,R> {
        private RulePassPostProcessor postProcessor;
        public Builder() {
            this.postProcessor = null;
        }

        public Builder<C,R> setPostProcessor(RulePassPostProcessor p) {
            this.postProcessor = p;
            return this;
        }

        public DefaultRuleEngine<C,R> build(){
            return new DefaultRuleEngine<>(this);
        }
    }
}
