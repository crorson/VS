package com.example.crudcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class CrudControllerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudControllerApplication.class, args);
    }

}
