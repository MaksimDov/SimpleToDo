package com.Maksim.SimpleToDo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication()
public class SimpleToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleToDoApplication.class, args);
	}

}
