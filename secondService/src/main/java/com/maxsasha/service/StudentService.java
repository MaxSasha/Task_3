package com.maxsasha.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;

import static com.maxsasha.api.redirector.Redirector.*;
import static com.maxsasha.api.transformer.StudentTransformer.*;
import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	public Student create(Student student) throws JsonProcessingException {
		Student createdStudent = redirectToCreate(transformToSecondServiceDto(student));
		createdStudent.setMidleName(student.getMidleName());
		return studentRepository.save(createdStudent);
	}

	public Student edit(Student student, String id) throws JsonProcessingException {
		student.setId(id);
		Student updatedStudent = redirectToUpdate(transformToSecondServiceDto(student));
		updatedStudent.setMidleName(student.getMidleName());
		return studentRepository.save(updatedStudent);
	}
}