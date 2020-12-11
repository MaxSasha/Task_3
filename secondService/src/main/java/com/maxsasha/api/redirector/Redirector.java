package com.maxsasha.api.redirector;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.api.dto.ThirdServiceStudentDto;
import com.maxsasha.entity.Student;

import reactor.core.publisher.Mono;

public class Redirector {
	private final static String URL_PUT_STUDENT = "http://thirdService:8083/api/students/";
	private final static WebClient webClient = WebClient.create(URL_PUT_STUDENT);

	public static Student redirectToCreate(ThirdServiceStudentDto dto) throws JsonProcessingException {
		return webClient.post().uri(URL_PUT_STUDENT)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(Mono.just(dto), Student.class)
				.retrieve().bodyToFlux(Student.class).blockFirst();
	}

	public static Student redirectToUpdate(ThirdServiceStudentDto dto) throws JsonProcessingException {
		return webClient.put().uri(URL_PUT_STUDENT + dto.getId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).body(Mono.just(dto), Student.class)
				.retrieve().bodyToMono(Student.class).block();
	}
}