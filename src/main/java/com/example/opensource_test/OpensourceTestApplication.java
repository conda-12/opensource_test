package com.example.opensource_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OpensourceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpensourceTestApplication.class, args);
    }

}
