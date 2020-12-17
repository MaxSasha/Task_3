package com.maxsasha.api.redirector;

import static com.maxsasha.api.transformer.StudentTransformer.transformToJson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.api.dto.SecondServiceStudentDto;
import com.maxsasha.entity.Student;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Redirector {
	@Value("${url_put_student}")
	private String URL_PUT_STUDENT;
	private final RestTemplate restTemplate;
	private final HttpHeaders headers;

	public Student redirectToCreate(SecondServiceStudentDto dto) throws JsonProcessingException {
		HttpEntity<String> requestEntity = new HttpEntity<String>(transformToJson(dto), headers);
		return restTemplate.exchange(URL_PUT_STUDENT, HttpMethod.POST, requestEntity, Student.class).getBody();
	}

	public Student redirectToUpdate(SecondServiceStudentDto dto) throws JsonProcessingException {
		HttpEntity<String> requestEntity = new HttpEntity<String>(transformToJson(dto), headers);
		return restTemplate.exchange(URL_PUT_STUDENT + dto.getId(), HttpMethod.PUT, requestEntity, Student.class)
				.getBody();
	}
}