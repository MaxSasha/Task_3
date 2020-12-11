package com.maxsasha.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.maxsasha.api.redirector.Redirector.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import static com.maxsasha.api.transformer.StudentTransformer.*;
import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	public Page<Student> getUsers(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public Student create(Student student) throws JsonProcessingException {
		Student createdStudent = redirectToCreate(transformToSecondServiceDto(student));
		createdStudent.setFisrtName(student.getFisrtName());
		return studentRepository.save(createdStudent);
	}

	public Student edit(Student student, String id) throws JsonProcessingException {
		student.setId(id);
		Student updatedStudent = redirectToUpdate(transformToSecondServiceDto(student));
		updatedStudent.setFisrtName(student.getFisrtName());
		return studentRepository.save(updatedStudent);
	}

	public void delete(String id) {
		studentRepository.deleteById(id);
	}
}