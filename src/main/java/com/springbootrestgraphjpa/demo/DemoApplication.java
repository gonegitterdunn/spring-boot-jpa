package com.springbootrestgraphjpa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({
  "com.springbootrestgraphjpa.controller",
  "com.springbootrestgraphjpa.service",
  "com.springbootrestgraphjpa.aop"
})
@EntityScan("com.springbootrestgraphjpa.entity")
@EnableJpaRepositories("com.springbootrestgraphjpa.repository")
@EnableScheduling
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }
}
