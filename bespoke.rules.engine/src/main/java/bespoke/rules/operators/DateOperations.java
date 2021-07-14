package bespoke.rules.operators;

import bespoke.enums.Operator;

import java.time.LocalDateTime;

public class DateOperations {
    public static boolean create(LocalDateTime operand1, Operator operator, LocalDateTime operand2) {
        switch (operator) {
            case GREATER_THAN -> operand1.isAfter(operand2);
            case SMALLER_THAN -> operand1.isBefore(operand2);
            case EQUAL_TO -> operand1.equals(operand2);
            default -> System.out.println("Unsupported date operation "+operator.name());
        }
        return false;
    }
}
