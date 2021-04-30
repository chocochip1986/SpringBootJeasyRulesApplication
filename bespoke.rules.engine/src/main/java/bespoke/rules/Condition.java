package bespoke.rules;

@FunctionalInterface
public interface Condition<COHORT> {
    boolean evaluate(COHORT c);
}
