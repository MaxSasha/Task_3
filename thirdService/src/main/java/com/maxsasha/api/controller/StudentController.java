package com.maxsasha.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.maxsasha.api.transformer.StudentTransformer.transform;
import com.maxsasha.api.controller.StudentController;
import com.maxsasha.api.dto.StudentDto;
import com.maxsasha.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
	private final StudentService service;

	@PostMapping
	public StudentDto create(@RequestBody StudentDto studentDto) {
		log.info("Received request to create student with info: lastName: {}", studentDto.getLastName());
		return transform(service.create(transform(studentDto)));
	}

	@PutMapping("/{id}")
	public StudentDto put(@PathVariable String id, @RequestBody StudentDto studentDto) {
		log.info("Received request to update student with info id: {}, lastName: {} ", id, studentDto.getLastName());
		return transform(service.edit(transform(studentDto), id));
	}
}