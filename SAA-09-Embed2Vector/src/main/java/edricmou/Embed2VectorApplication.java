package edricmou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Embed2VectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(Embed2VectorApplication.class, args);
    }
}
