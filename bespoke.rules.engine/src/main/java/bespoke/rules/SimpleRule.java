package bespoke.rules;

import lombok.Data;

import java.util.List;

@Data
public class SimpleRule<C> implements Rule<C> {
    private String name;
    private String description;
    private int priority;
    private Condition<C> condition;
    private List<Action> actions;

    public SimpleRule() {
        this.name = "Rule Name";
        this.description = "Rule Description";
        this.priority = 2147483647;
    }

    public SimpleRule<C> name(String name) {
        this.name = name;
        return this;
    }

    public SimpleRule<C> description(String description) {
        this.description = description;
        return this;
    }

    public SimpleRule<C> priority(int priority) {
        this.priority = priority;
        return this;
    }

    public SimpleRule<C> when(Condition<C> condition) {
        this.condition = condition;
        return this;
    }

    public SimpleRule<C> then(Action action) {
        this.actions.add(action);
        return this;
    }

    @Override
    public boolean evaluate(C c) {
        return this.condition.evaluate(c);
    }

    @Override
    public void execute() {
        for(int i = 0 ; i < this.actions.size() ; i++) {
            this.actions.get(i).execute();
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
    public int compareTo(Rule<C> rule) {
        if (this.priority < rule.getPriority()) {
            return -1;
        } else {
            return this.priority > rule.getPriority() ? 1 : 0;
        }
    }
}
