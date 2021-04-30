package bespoke;

import bespoke.entities.Animal;
import bespoke.rules.SimpleRule;

public class Runner {
    public void run() {
        Animal animal = Animal.builder().name("Spark").build();
        SimpleRule<Animal> rule = new SimpleRule<>();
        rule.name("hello")
                .description("hihi")
                .when((c)-> animal.getName().equals("Spark"))
                .then(() -> {});
    }
}
