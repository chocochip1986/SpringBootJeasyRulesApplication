package bespoke.rules;

@FunctionalInterface
public interface Action<RESULT> {
    void execute(RESULT r);
}
