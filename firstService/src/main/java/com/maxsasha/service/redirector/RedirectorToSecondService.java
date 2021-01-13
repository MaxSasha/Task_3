package com.maxsasha.service.redirector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.maxsasha.api.dto.SecondServiceStudentDto;
import com.maxsasha.entity.Student;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedirectorToSecondService {
    @Value("${second.url}")
    private String secondServiceUrl;
    private final String endPoint = "/api/students/";//ask
    private final RestTemplate restTemplate;
    private final HttpHeaders headers = new HttpHeaders();
    private final ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    public Student redirectToCreate(SecondServiceStudentDto dto) throws JsonProcessingException {
        headers.setContentType(new MediaType("application", "json"));
        HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(dto), headers);
        return restTemplate.exchange(secondServiceUrl + endPoint, HttpMethod.POST, requestEntity, Student.class)
                .getBody();
    }

    public Student redirectToUpdate(SecondServiceStudentDto dto) throws JsonProcessingException {
        headers.setContentType(new MediaType("application", "json"));
        HttpEntity<String> requestEntity = new HttpEntity<String>(mapper.writeValueAsString(dto), headers);
        return restTemplate
                .exchange(secondServiceUrl + endPoint + dto.getId(), HttpMethod.PUT, requestEntity, Student.class)
                .getBody();
    }
}