package bespoke.rule.engine;

import bespoke.rules.Rules;

import java.util.List;

public interface RuleEngine<C> {
    void trigger(Rules<C> rules, C c);
}
