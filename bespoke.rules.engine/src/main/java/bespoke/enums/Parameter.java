package bespoke.enums;

import bespoke.rules.Condition;
import bespoke.rules.CreateRuleFunction;
import bespoke.rules.operators.DateOperations;
import bespoke.rules.operators.StringOperations;
import bespoke.rules.poojo.RuleEngineSubject;

import java.time.LocalDateTime;


public enum Parameter implements CreateRuleFunction<RuleEngineSubject> {
    LIVING_STATUS("LIVING_STATUS") {
        @Override
        public Condition<RuleEngineSubject> create(Operator o, String val) {
            return (s) -> s.getPerson().getDeathDate() == null;
        }
    },
    NATIONALITY("NATIONALITY") {
        @Override
        public Condition<RuleEngineSubject> create(Operator operator, String value) {
            return (s) -> StringOperations.operation(s.getPerson().getNationality().name(), operator, value) &&
                    DateOperations.create(s.getPerson().getCitizenshipAttainmentDate(), operator, LocalDateTime.now());
        }
    },
    ADDRESS("ADDRESS") {
        @Override
        public Condition<RuleEngineSubject> create(Operator operator, String value) {
            return (s) -> StringOperations.operation(s.getAddress().getAddress(), operator, value);
        }
    },
    AGE("AGE") {
        @Override
        public Condition<RuleEngineSubject> create(Operator operator, String value) {
            return (s) -> s.getPerson().getBirthDate() != null;
        }
    };
//    SIGN_UP_STATUS("SIGN_UP_STATUS"),
//    INCOME("INCOME"),

    private final String name;
    Parameter(String n) {
        this.name = n;
    }
}
