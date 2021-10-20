package com.springbootrestgraphjpa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.springbootrestgraphjpa.controller", "com.springbootrestgraphjpa.service"})
@EntityScan("com.springbootrestgraphjpa.entity")
@EnableJpaRepositories("com.springbootrestgraphjpa.repository")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
