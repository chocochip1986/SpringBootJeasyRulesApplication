package bespoke.rules;

@FunctionalInterface
public interface Action<COHORT, RESULT> {
    void execute(COHORT c, RESULT r);
}
