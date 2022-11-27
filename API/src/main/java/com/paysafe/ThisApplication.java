package com.paysafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.paysafe"})
public class ThisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThisApplication.class,args);
    }
}
