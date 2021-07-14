package bespoke.rules;

import java.util.List;

public interface Rule<C,R> extends Comparable<Rule<C,R>> {
    boolean evaluate(C c);

    void execute(R r, C c);

    default Condition<C> getCondition() { return null; };
    default List<Action<C,R>> getActions() { return null; };
    default List<Action<C,R>> getFailureActions() {return null;}
    default String getRuleName() { return "Default Rule Name"; };
    default String getRuleDescription() { return "Default Rule Description"; };
    int getPriority();
}
