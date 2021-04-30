package bespoke.rules;

import java.util.List;

public interface Rule<C> extends Comparable<Rule<C>> {
    boolean evaluate(C c);

    void execute();

    default Condition<C> getCondition() { return null; };
    default List<Action> getActions() { return null; };
    default String getRuleName() { return "Default Rule Name"; };
    default String getRuleDescription() { return "Default Rule Description"; };
    int getPriority();
}
