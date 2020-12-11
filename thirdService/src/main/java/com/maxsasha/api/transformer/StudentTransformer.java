package com.maxsasha.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.maxsasha.api.dto.StudentDto;
import com.maxsasha.entity.Student;

public class StudentTransformer {
	
	public static StudentDto transform(Student student) {
		return StudentDto.builder().id(student.getId()).lastName(student.getLastName()).build();
	}

	public static Student transform(StudentDto dto) {
		return Student.builder().id(dto.getId()).lastName(dto.getLastName()).build();
	}
}