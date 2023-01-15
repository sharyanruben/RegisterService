package com.springbootexercise.springbootexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringbootExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootExerciseApplication.class, args);
	}

}
