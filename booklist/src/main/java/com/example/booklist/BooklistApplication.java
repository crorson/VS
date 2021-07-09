package com.example.booklist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@PropertySource("classpath:application-postgres.properties")
public class BooklistApplication {

//    @RefreshScope
//    @RestController
//    class MessageRestController {
//
//        @Value("${message:Hello default}")
//        private String message;
//
//        @RequestMapping("/message")
//        String getMessage() {
//            return this.message;
//        }
//    }

    public static void main(String[] args) {
        SpringApplication.run(BooklistApplication.class, args);
    }

}
