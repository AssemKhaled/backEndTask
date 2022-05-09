package com.example.backEndTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.example.backEndTask"})
@EnableScheduling
@EnableSwagger2
public class BackEndTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackEndTaskApplication.class, args);
	}

}
