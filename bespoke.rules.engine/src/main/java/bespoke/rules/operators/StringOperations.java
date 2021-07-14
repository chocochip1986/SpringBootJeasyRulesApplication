package bespoke.rules.operators;

import bespoke.enums.Operator;

public class StringOperations {
    public static boolean operation(String operand1, Operator operator, String operand2) {
        switch (operator) {
            case EQUAL_TO -> operand1.equals(operand2);
            default -> System.out.println("Unsupported String operation -> "+operator.name());
        }
        return false;
    }
}
