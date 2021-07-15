package bespoke.rule.engine;

@FunctionalInterface
public interface RuleEngineProcessor<C> {
    void execute(C c);
}
