package com.maxsasha.services.currentService.api.controller;

import static com.maxsasha.services.currentService.api.transformer.StudentTransformer.transform;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.services.currentService.api.dto.StudentDto;
import com.maxsasha.services.currentService.entity.Student;
import com.maxsasha.services.currentService.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody StudentDto studentDto) {
        log.info("Received request to create Student with info: middleName: {}, lastName: {}",
                studentDto.getMiddleName(), studentDto.getLastName());
        try {
            Student student = studentService.create(transform(studentDto));
            log.info("Create student second service: " + student);
            return ResponseEntity.status(201).body(student);
        } catch (JsonProcessingException ex) {
            log.error("Error to parse entity to json in create method. With info: exception message:{}", ex);
            return ResponseEntity.status(505).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> put(@PathVariable String id, @RequestBody StudentDto studentDto) {
        log.info("Received request to update Student with info: id:{}, middleName:{}, lastName:{}", id,
                studentDto.getMiddleName(), studentDto.getLastName());
        try {
            Student student = studentService.update(transform(studentDto), id);
            return ResponseEntity.status(200).body(transform(student));
        } catch (JsonProcessingException ex) {
            log.error("Error to parse entity to json in update method. With info: student id:{}, exception message:{}",
                    id, ex);
            return ResponseEntity.status(505).build();
        }
    }
}