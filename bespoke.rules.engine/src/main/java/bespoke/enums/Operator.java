package bespoke.enums;

public enum Operator {
    EQUAL_TO("EQUAL_TO"),
    GREATER_THAN("GREATER_THAN"),
    SMALLER_THAN("SMALLER_THAN"),
    CONTAINS("CONTAINS");
    private final String name;
    Operator(String n) {
        this.name = n;
    }
}
