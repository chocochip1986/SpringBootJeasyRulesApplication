package bespoke.rules;

import bespoke.enums.Operator;

import java.util.function.Function;

public interface CreateRuleFunction<C> {
    Condition<C> create(Operator operator, String value);
}
