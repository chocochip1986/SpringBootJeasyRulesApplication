package jeasy.schemes;

import jeasy.rules.commons.MustBeSingaporeanRule;
import jeasy.rules.commons.OLevelsMustBeStraightAsRule;
import lombok.Data;
import org.jeasy.rules.api.Rules;

@Data
public class TrueBlueSingaporeanScheme extends AbstractScheme {
    public TrueBlueSingaporeanScheme(Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.rules = builder.rules;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final String name = "True Blue Singaporean Scheme";
        private final String description = "This scheme is for singaporeans ONLY";
        private final Rules rules = new Rules(new MustBeSingaporeanRule(), new OLevelsMustBeStraightAsRule());

        public TrueBlueSingaporeanScheme build() {
            return new TrueBlueSingaporeanScheme(this);
        }
    }
}
