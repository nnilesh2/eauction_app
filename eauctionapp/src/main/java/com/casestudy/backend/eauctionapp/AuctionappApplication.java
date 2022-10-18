package com.casestudy.backend.eauctionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.casestudy.backend.eauctionapp.eauctiondao")
public class AuctionappApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuctionappApplication.class, args);
	}

}