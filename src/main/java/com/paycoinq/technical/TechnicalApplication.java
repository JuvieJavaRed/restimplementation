package com.paycoinq.technical;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.paycoinq.technical")
@OpenAPIDefinition
public class TechnicalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalApplication.class, args);
	}

}
