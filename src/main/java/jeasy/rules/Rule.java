package jeasy.rules;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;

@org.jeasy.rules.annotation.Rule(name = "weather rule", description = "If it rains")
public class Rule {
    @Condition
    public boolean ifItRains(@Fact("rain") boolean rain) {
        return rain;
    }

    @Action
    public void takeAnUmbrella() {
        System.out.println("It rains, take an umbrella");
    }
}
