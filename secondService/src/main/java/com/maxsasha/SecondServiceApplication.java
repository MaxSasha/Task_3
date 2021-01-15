package com.maxsasha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SecondServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondServiceApplication.class, args);
    }
}