package com.maxsasha.service;

import static com.maxsasha.api.transformer.StudentTransformer.transformToSecondServiceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.api.redirector.Redirector;
import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;
	private final Redirector redirect;
	
	public Page<Student> getUsers(Pageable pageable) {
		return studentRepository.findAll(pageable);
	}

	public Student create(Student student) throws JsonProcessingException {
		Student createdStudent = redirect.redirectToCreate(transformToSecondServiceDto(student));
		createdStudent.setFisrtName(student.getFisrtName());
		return studentRepository.save(createdStudent);
	}

	public Student update(Student student, String id) throws JsonProcessingException {
		student.setId(id);
		Student updatedStudent = redirect.redirectToUpdate(transformToSecondServiceDto(student));
		updatedStudent.setFisrtName(student.getFisrtName());
		return studentRepository.save(updatedStudent);
	}

	public void delete(String id) {
		studentRepository.deleteById(id);
	}
}