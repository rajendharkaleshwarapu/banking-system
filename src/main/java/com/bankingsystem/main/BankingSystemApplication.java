package com.bankingsystem.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.bankingsystem.repository")
@EntityScan(basePackages = "com.bankingsystem.model")
@ComponentScan(basePackages = "com.bankingsystem")
public class BankingSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
        System.out.println("Banking System Application Started on port 8080");
    }
}
