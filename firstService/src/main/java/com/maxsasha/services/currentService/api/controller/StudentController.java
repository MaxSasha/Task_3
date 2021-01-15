package com.maxsasha.services.currentService.api.controller;

import static com.maxsasha.services.currentService.api.transformer.StudentTransformer.transform;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public Page<Student> get(@RequestParam(defaultValue = "${default.page}") Integer page,
            @RequestParam(defaultValue = "${default.size}") Integer size) {
        log.info("Received request to get students. Page size: {}, current Page: {}", size, page);
        return studentService.getUsers(PageRequest.of(page, size));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody StudentDto studentDto) {
        log.info("Received request to create Student with info: firstName: {}, middleName: {}, lastName: {}",
                studentDto.getFirstName(), studentDto.getMiddleName(), studentDto.getLastName());
        try {
            StudentDto stud = transform(studentService.create(transform(studentDto)));
            return ResponseEntity.status(201).body(stud);
        } catch (JsonProcessingException ex) {
            log.error("Error to parse entity to json in post method. exception message:{}", ex);
            return ResponseEntity.status(505).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id, @RequestBody StudentDto studentDto) {
        log.info("Received request to update Student with info: id:{}, firstName:{}, middleName:{}, lastName:{}", id,
                studentDto.getFirstName(), studentDto.getMiddleName(), studentDto.getLastName());
        try {
            Student student = studentService.update(transform(studentDto), id);
            return ResponseEntity.status(200).body(transform(student));
        } catch (JsonProcessingException ex) {
            log.error("Error to parse entity to json in update method. With info: student id:{}, exception message:{}",
                    id, ex);
            return ResponseEntity.status(505).build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        log.info("Received request to delete student id: {}", id);
        studentService.delete(id);
    }
}