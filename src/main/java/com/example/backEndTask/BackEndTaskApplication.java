package com.example.backEndTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackEndTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndTaskApplication.class, args);
	}

}
