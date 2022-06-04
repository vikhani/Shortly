package com.vkhani.Shortly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableAutoConfiguration
@ComponentScan
public class ShortlyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortlyApplication.class, args);
	}

}
