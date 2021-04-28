package jeasy.multi.threaded.services.steps.rules;


import java.util.List;

@FunctionalInterface
public interface GenerateRules<C,R> {
    R generateRules(List<C> c);
}
