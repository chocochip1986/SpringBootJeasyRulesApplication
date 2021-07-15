package bespoke.rules.operations;

import bespoke.enums.Operator;

import java.time.LocalDateTime;

public class DateOperations {
    public static boolean create(LocalDateTime operand1, Operator operator, LocalDateTime operand2) {
        switch (operator) {
            case GREATER_THAN -> {
                return operand1.isAfter(operand2);
            }
            case SMALLER_THAN -> {
                return operand1.isBefore(operand2);
            }
            case EQUAL_TO -> {
                return operand1.equals(operand2);
            }
            default -> System.out.println("Unsupported date operation "+operator.name());
        }
        return false;
    }


}
