package com.example.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpHeaders;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Collections;

@SpringBootApplication
public class RestapiApplication {

    WebClient client = WebClient.create();

    //Preparing a Request
    //Define the method
    WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();
    //Define the URL
    WebClient.RequestBodySpec bodySpec = uriSpec.uri("/resource");
    //Define the body
    WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.bodyValue("data");
    //Define the Headers
    WebClient.ResponseSpec responseSpec = headersSpec.header(
            MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .acceptCharset(StandardCharsets.UTF_8)
            .ifNoneMatch("*")
            .ifModifiedSince(ZonedDateTime.now())
            .retrieve();

    //Getting a response
    Mono<String> response = headersSpec.exchangeToMono(response -> {
        if (response.statusCode()
                .equals(HttpStatus.OK)) {
            return response.bodyToMono(String.class);
        } else if (response.statusCode()
                .is4xxClientError()) {
            return Mono.just("Error response");
        } else {
            return response.createException()
                    .flatMap(Mono::error);
        }
    });

//    private static final Logger log = LoggerFactory.getLogger(RestapiApplication.class); //A logger, to send output to the log (the console, in this example).

    public static void main(String[] args) {
        SpringApplication.run(RestapiApplication.class, args);
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) { //A RestTemplate, which uses the Jackson JSON processing library to process the incoming data.
//        return builder.build();
//    }
//
//    @Bean
//    public CommandLineRunner run(RestTemplate restTemplate) throws Exception { //A CommandLineRunner that runs the RestTemplate (and, consequently, fetches our quotation) on startup.
//        return args -> {
//            Quote quote = restTemplate.getForObject("https://quoters.apps.pcfone.io/api/random", Quote.class);
//            log.info(quote.toString());
//        };
//    }
}
