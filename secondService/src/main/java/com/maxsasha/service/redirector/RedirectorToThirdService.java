package com.maxsasha.service.redirector;

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
public class RedirectorToThirdService {
    @Value("${third.service.url}")
    private String thirdServiceUrl;
    private final String endPoint = "/api/students/";
    private final WebClient webClient;

    public Student redirectToCreate(ThirdServiceStudentDto dto) throws JsonProcessingException {
        return webClient.post().uri(thirdServiceUrl + endPoint)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(dto), Student.class)
                .retrieve()
                .bodyToFlux(Student.class)
                .blockFirst();
    }

    public Student redirectToUpdate(ThirdServiceStudentDto dto) throws JsonProcessingException {
        return webClient.put().uri(thirdServiceUrl + endPoint + dto.getId())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(dto), Student.class)
                .retrieve()
                .bodyToMono(Student.class)
                .block();
    }
}