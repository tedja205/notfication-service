package com.andreas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.andreas.models")
@ComponentScan("com.andreas.*")
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting");
        SpringApplication.run(Main.class, args);
    }
}