package jeasy.rules.commons;

import jeasy.enums.Gender;
import jeasy.enums.Nationality;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;

@Rule(name = "Die die must be True Blue Singaporean", description = "Read the name, nuff said")
public class MustBeSingaporeanRule {
    @Condition
    public boolean when(@Fact("name") String name, @Fact("nationality") Nationality nationality) {
        System.out.println("Checking if "+name+" is Singaporean...");
        return nationality.equals(Nationality.SINGAPOREAN);
    }

    @Action
    public void action(@Fact("name") String name, @Fact("gender") Gender gender) {
        System.out.println("Swee la this "+name+" "+(gender.equals(Gender.MALE) ? "yan dao" : "chio bu"+ "is Singaporean"));
    }
}
