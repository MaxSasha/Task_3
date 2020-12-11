package com.maxsasha.api.redirector;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static com.maxsasha.api.transformer.StudentTransformer.transformToJson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.api.dto.SecondServiceStudentDto;
import com.maxsasha.entity.Student;

public class Redirector {
	private static final String URL_PUT_STUDENT = "http://secondService:8082/api/students/";
	private static final RestTemplate restTemplate;
	private static final HttpHeaders headers;

	static {
		restTemplate = new RestTemplate();
		headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json"));
	}

	public static Student redirectToCreate(SecondServiceStudentDto dto) throws JsonProcessingException {
		HttpEntity<String> requestEntity = new HttpEntity<String>(transformToJson(dto), headers);
		return restTemplate.exchange(URL_PUT_STUDENT, HttpMethod.POST, requestEntity, Student.class)
				.getBody();
	}

	public static Student redirectToUpdate(SecondServiceStudentDto dto) throws JsonProcessingException {
		HttpEntity<String> requestEntity = new HttpEntity<String>(transformToJson(dto), headers);
		return restTemplate
				.exchange(URL_PUT_STUDENT + dto.getId(), HttpMethod.PUT, requestEntity, Student.class)
				.getBody();
	}
}