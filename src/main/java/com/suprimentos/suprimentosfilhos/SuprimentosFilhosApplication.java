package com.suprimentos.suprimentosfilhos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SuprimentosFilhosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuprimentosFilhosApplication.class, args);
	}

}
