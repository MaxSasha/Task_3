package com.maxsasha.services.thirdService.redirector;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.maxsasha.services.currentService.entity.Student;
import com.maxsasha.services.thirdService.dto.StudentDto;

@FeignClient(name = "thirdService", url = "${third.service.url}")
public interface RedirectorToThirdService {

    @PostMapping(value = "/api/students/", consumes = "application/json", produces = "application/json")
    Student redirectToCreate(StudentDto dto);

    @PutMapping(value = "/api/students/{id}", consumes = "application/json", produces = "application/json")
    Student redirectToUpdate(@PathVariable String id, StudentDto dto);
}