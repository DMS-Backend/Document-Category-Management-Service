package com.example.hello_world_auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class HelloWorldAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldAuthApplication.class, args);
	}

}
