package bespoke.rule.engine;

import bespoke.rules.Rules;


public interface RuleEngine<C,R> {
    void trigger(Rules<C,R> rules, C c, R r1, R r2);
}
