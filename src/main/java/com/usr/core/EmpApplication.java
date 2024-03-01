package com.usr.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.usr.*")
@EntityScan(basePackages = "com.usr.entity")
@EnableJpaRepositories(basePackages = "com.usr.dao")
public class EmpApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmpApplication.class, args);
    }

}
