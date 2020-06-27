package com.ibm.humana.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;

@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
public class HumanaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanaServerApplication.class, args);
	}

}
