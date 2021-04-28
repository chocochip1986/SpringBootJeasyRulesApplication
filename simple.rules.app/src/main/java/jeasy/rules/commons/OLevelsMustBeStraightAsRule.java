package jeasy.rules.commons;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "O Levels must be 6 points and below", description = "O Levels must be 6 points and below")
public class OLevelsMustBeStraightAsRule {
    @Condition
    public boolean condition(@Fact("name") String name, @Fact("tookOLevels") boolean tookOLevels, @Fact("oLevelScore") int oLevelScore) {
        System.out.println("Checking if "+name+" is OP enough");
        return tookOLevels && oLevelScore <= 6;
    }

    @Action
    public void action(@Fact("name") String name) {
        System.out.println(name+" streamrolled his/her O Levels!");
    }
}
