package pl.sda.intermediate12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication

public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }
}
