package otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import otus.front.FrontendServer;

@SpringBootApplication
public class Frontend {
    public static void main(String[] args) {
        SpringApplication.run(Frontend.class, args);
    }
}
