package com.maxsasha.api.controller;

import static com.maxsasha.api.transformer.StudentTransformer.transform;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxsasha.api.dto.StudentDto;
import com.maxsasha.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
	private final StudentService studentService;

	@PostMapping
	public StudentDto create(@RequestBody StudentDto studentDto) {
		log.info("Received request to create student with info: lastName: {}", studentDto.getLastName());
		return transform(studentService.create(transform(studentDto)));
	}

	@PutMapping("/{id}")
	public StudentDto put(@PathVariable String id, @RequestBody StudentDto studentDto) {
		log.info("Received request to update student with info id: {}, lastName: {} ", id, studentDto.getLastName());
		return transform(studentService.edit(transform(studentDto), id));
	}
}