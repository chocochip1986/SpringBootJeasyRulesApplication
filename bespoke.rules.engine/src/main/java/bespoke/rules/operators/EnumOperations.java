package bespoke.rules.operators;

import bespoke.enums.Operator;

public class EnumOperations {
    public static boolean operation(Enum<?> operand1, Operator operator, Enum<?> operand2) {
        switch (operator) {
            case EQUAL_TO -> operand1.equals(operand2);
            default -> System.out.println("Unsupported enum operation "+operator.name());
        }
        return  false;
    }
}
