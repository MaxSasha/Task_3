package com.maxsasha.service;

import org.springframework.stereotype.Service;

import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
	private final StudentRepository studentRepository;

	public Student create(Student student) {
	    return studentRepository.insert(student);
	}

	public Student edit(Student student, String id) {
		student.setId(id);
		studentRepository.updateDocumentLastName(id, student.getLastName());
		return student;
	}
}