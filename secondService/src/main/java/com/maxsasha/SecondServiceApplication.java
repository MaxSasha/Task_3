package com.maxsasha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import com.maxsasha.api.redirector.Redirector;

@SpringBootApplication
public class SecondServiceApplication {
	
	@Bean
	public WebClient webClient() {
		return WebClient.create();
	}

	@Bean
	public Redirector redirector() {
		return new Redirector(webClient());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SecondServiceApplication.class, args);
	}

}
