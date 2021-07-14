package bespoke.rules;

import java.util.List;

public interface Rule<C,R> extends Comparable<Rule<C,R>> {
    boolean evaluate(C c);

    void execute(R r);

    default Condition<C> getCondition() { return null; };
    default List<Action<R>> getActions() { return null; };
    default String getRuleName() { return "Default Rule Name"; };
    default String getRuleDescription() { return "Default Rule Description"; };
    int getPriority();
}
