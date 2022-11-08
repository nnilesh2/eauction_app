package com.eauction.eauctionawslambda;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.eauction.eauctionawslambda.eauctiondao")
public class EAuctionAWSLambda {

	public static void main(String[] args) {
		FunctionalSpringApplication.run(EAuctionAWSLambda.class, args);
	}

	@Bean
	public ObjectMapper objectMapper(){
		return new ObjectMapper();
	}
}
