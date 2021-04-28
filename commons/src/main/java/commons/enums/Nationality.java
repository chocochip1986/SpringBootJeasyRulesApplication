package commons.enums;

import java.util.Random;

public enum Nationality {
    SINGAPOREAN,
    NON_SINGAPOREAN;

    public static Nationality pick() {
        return Nationality.values()[new Random().nextInt(Nationality.values().length)];
    }
}
