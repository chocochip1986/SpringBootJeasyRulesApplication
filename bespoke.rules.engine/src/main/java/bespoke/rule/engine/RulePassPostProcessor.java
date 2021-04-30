package bespoke.rule.engine;

@FunctionalInterface
public interface RulePassPostProcessor {
    void execute();
}
