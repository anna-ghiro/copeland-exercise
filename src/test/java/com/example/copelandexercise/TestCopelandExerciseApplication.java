package com.example.copelandexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCopelandExerciseApplication {

	public static void main(String[] args) {
		SpringApplication.from(CopelandExerciseApplication::main).with(TestCopelandExerciseApplication.class).run(args);
	}

}
