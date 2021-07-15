package bespoke.rules;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleRule<C,R> implements Rule<C,R> {
    private String name;
    private String description;
    private int priority;
    private Condition<C> condition;
    private List<Action<C,R>> actions;
    private List<Action<C,R>> failureActions;

    public SimpleRule() {
        this.name = "Rule Name";
        this.description = "Rule Description";
        this.priority = 2147483647;
        this.actions = new ArrayList<>();
        this.failureActions = new ArrayList<>();
    }

    public SimpleRule<C,R> name(String name) {
        this.name = name;
        return this;
    }

    public SimpleRule<C,R> description(String description) {
        this.description = description;
        return this;
    }

    public SimpleRule<C,R> priority(int priority) {
        this.priority = priority;
        return this;
    }

    public SimpleRule<C,R> when(Condition<C> condition) {
        this.condition = condition;
        return this;
    }

    public SimpleRule<C,R> then(Action<C, R> action) {
        this.actions.add(action);
        return this;
    }

    public SimpleRule<C,R> orElse(Action<C, R> action) {
        this.failureActions.add(action);
        return this;
    }

    @Override
    public boolean evaluate(C c) {
        return this.condition.evaluate(c);
    }

    @Override
    public void execute(R r, C c) {
        for(int i = 0 ; i < this.actions.size() ; i++) {
            this.actions.get(i).execute(c, r);
        }
    }

    @Override
    public String getRuleName() {
        return this.name;
    }

    @Override
    public String getRuleDescription() {
        return this.description;
    }

    @Override
    public int compareTo(Rule<C,R> rule) {
        if (this.priority < rule.getPriority()) {
            return -1;
        } else {
            return this.priority > rule.getPriority() ? 1 : 0;
        }
    }
}
