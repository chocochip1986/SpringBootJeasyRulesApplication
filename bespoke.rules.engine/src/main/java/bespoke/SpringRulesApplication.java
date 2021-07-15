package bespoke;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRulesApplication {
    public static void main(String[] args) {
        try {
            SpringApplication.run(SpringRulesApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
