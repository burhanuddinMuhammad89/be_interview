package com.example.demo;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.example.demo"})
@EntityScan("com.example.demo.model")
@EnableJpaRepositories("com.example.demo.repository")
public class DemoApplication{

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC+7:00"));
		SpringApplication.run(DemoApplication.class, args);
	}

}
