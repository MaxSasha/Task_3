package com.maxsasha.service;

import static com.maxsasha.api.transformer.StudentTransformer.transformToThirdServiceDto;

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
	
	public Student create(Student student) throws JsonProcessingException {
		Student createdStudent = redirect.redirectToCreate(transformToThirdServiceDto(student));
		createdStudent.setMidleName(student.getMidleName());
		return studentRepository.save(createdStudent);
	}

	public Student update(Student student, String id) throws JsonProcessingException {
		student.setId(id);
		Student updatedStudent = redirect.redirectToUpdate(transformToThirdServiceDto(student));
		updatedStudent.setMidleName(student.getMidleName());
		return studentRepository.save(updatedStudent);
	}
}