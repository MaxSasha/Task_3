package com.maxsasha.api.redirector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.maxsasha.api.dto.ThirdServiceStudentDto;
import com.maxsasha.entity.Student;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class Redirector {
	@Value("${url_put_student}")
	private String URL_PUT_STUDENT;
	private final WebClient webClient;

	public Student redirectToCreate(ThirdServiceStudentDto dto) throws JsonProcessingException {
		return webClient.post()
				.uri(URL_PUT_STUDENT)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(dto), Student.class)
				.retrieve().bodyToFlux(Student.class)
				.blockFirst();
	}

	public Student redirectToUpdate(ThirdServiceStudentDto dto) throws JsonProcessingException {
		return webClient.put()
				.uri(URL_PUT_STUDENT + dto.getId())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(Mono.just(dto), Student.class)
				.retrieve().bodyToMono(Student.class)
				.block();
	}
}