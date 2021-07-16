package bespoke.rules;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Rules<C,R> {
    private TreeSet<Rule<C,R>> rules;

    public Rules() {
        this.rules = new TreeSet<>();
    }

    public void addRules(Rule<C,R>... rules) {
        Collections.addAll(this.rules, rules);
    }

    public void addRule(Rule<C,R> rule) {
        if( this.rules.contains(rule) ) {
        } else {
            this.rules.add(rule);
        }
    }

    public boolean removeRule(Rule<C,R> rule) {
        return this.rules.remove(rule);
    }

    public Rule<C,R> findRuleByName(Rule<C,R> rule) {
        return this.rules.stream().filter( r -> r.getRuleName().equals(rule.getRuleName()) ).findFirst().orElse(null);
    }

    public boolean isEmpty() {
        return this.rules.isEmpty();
    }

    public void purge() {
        this.rules.clear();
    }

    public Iterator<Rule<C,R>> getIterator() {
        return this.rules.iterator();
    }
}
