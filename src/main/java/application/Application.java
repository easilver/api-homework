package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The base of the application
 * Created by erica on 1/7/17.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"application.controllers", "application.repositories", "application.resources"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}