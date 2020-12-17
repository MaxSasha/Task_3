package com.maxsasha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.maxsasha.api.redirector.Redirector;

@SpringBootApplication
public class FirstServiceApplication {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public HttpHeaders httpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json"));
		return headers;
	}

	@Bean
	public Redirector redirector() {
		return new Redirector(restTemplate(), httpHeaders());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, args);
	}
}