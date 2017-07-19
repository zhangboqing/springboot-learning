package com.zbq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAsync
public class Sb10tenApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb10tenApplication.class, args);
	}
}
