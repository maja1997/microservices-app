package com.microservices.botdetector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BotDetectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(BotDetectorApplication.class, args);
    }
}
