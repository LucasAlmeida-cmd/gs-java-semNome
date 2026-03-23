package com.example.gs_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GsJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsJavaApplication.class, args);
	}

}
