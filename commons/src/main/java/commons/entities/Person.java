package commons.entities;

import com.github.javafaker.Faker;
import commons.enums.Gender;
import commons.enums.Nationality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private double height;
    private double weight;
    private int age;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int psleScore;
    private boolean tookOLevels;
    private int oLevelScore;
    private boolean tookALevels;
    private int aLevelScore;

    public static Person createRandom() {
        Random random = new Random();
        Person person =  Person.builder()
                .gender(Gender.pick())
                .name(Faker.instance().name().fullName())
                .height(1.5 + 0.5 * random.nextDouble())
                .age(20 + random.nextInt(10))
                .weight(50 + 30 * random.nextDouble())
                .psleScore(150 + random.nextInt(150))
                .tookALevels(random.nextBoolean())
                .tookOLevels(random.nextBoolean())
                .nationality(Nationality.pick())
                .build();
        if ( person.tookALevels ) {
            person.aLevelScore = random.nextInt(6);
        }
        if ( person.tookOLevels ) {
            person.oLevelScore = random.nextInt(20);
        }
        return person;
    }
}
