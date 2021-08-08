package com.bcp.challenge.exchange.rate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "APIs", version = "3.0", description = "Documentation APIs v3.0" ))
@SpringBootApplication
public class ApiExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiExchangeRateApplication.class, args);
	}

}
