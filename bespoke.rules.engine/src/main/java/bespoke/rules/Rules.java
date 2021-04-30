package bespoke.rules;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Rules<C> {
    private TreeSet<Rule<C>> rules;

    public Rules() {
        this.rules = new TreeSet<>();
    }

    public void addRules(Rule<C>... rules) {
        Collections.addAll(this.rules, rules);
    }

    public void addRule(Rule<C> rule) {
        if( this.rules.contains(rule) ) {
            this.rules.remove(rule);
        } else {
            this.rules.add(rule);
        }
    }

    public boolean removeRule(Rule<C> rule) {
        return this.rules.remove(rule);
    }

    public Rule<C> findRuleByName(Rule<C> rule) {
        return this.rules.stream().filter( r -> r.getRuleName().equals(rule.getRuleName()) ).findFirst().orElse(null);
    }

    public boolean isEmpty() {
        return this.rules.isEmpty();
    }

    public void purge() {
        this.rules.clear();
    }

    public Iterator<Rule<C>> getIterator() {
        return this.rules.iterator();
    }
}
