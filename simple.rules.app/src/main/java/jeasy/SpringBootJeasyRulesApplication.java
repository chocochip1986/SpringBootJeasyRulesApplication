package jeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(
        scanBasePackages = {
                "jeasy",
                "commons.entities"
        }
)
@EntityScan(basePackages = "commons.entities")
public class SpringBootJeasyRulesApplication {
    @PostConstruct
    public void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJeasyRulesApplication.class, args);
    }

    private void run() {
    }
}
