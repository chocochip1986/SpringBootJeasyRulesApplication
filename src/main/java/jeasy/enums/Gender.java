package jeasy.enums;

import java.util.Random;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender pick() {
        return Gender.values()[new Random().nextInt(Gender.values().length)];
    }
}
