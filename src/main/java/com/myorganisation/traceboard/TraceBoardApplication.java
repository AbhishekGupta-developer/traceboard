package com.myorganisation.traceboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class
TraceBoardApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraceBoardApplication.class, args);
	}

}
