package org.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"org.example", "org.example.controller", "org.example.service", "org.example.entity"})
public class OrderAppApplication {
    public static void main(String[] args) {
        log.debug("Starting OrderAppApplication");
        SpringApplication.run(OrderAppApplication.class, args);
    }
}