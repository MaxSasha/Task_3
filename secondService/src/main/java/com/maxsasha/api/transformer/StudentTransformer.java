package com.maxsasha.api.transformer;

import com.maxsasha.api.dto.StudentDto;
import com.maxsasha.api.dto.ThirdServiceStudentDto;
import com.maxsasha.entity.Student;

public class StudentTransformer {

	public static StudentDto transform(Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.midleName(student.getMidleName())
				.lastName(student.getLastName())
				.build();
	}

	public static Student transform(StudentDto dto) {
		return Student.builder()
				.id(dto.getId())
				.midleName(dto.getMidleName())
				.lastName(dto.getLastName())
				.build();
	}

	// convert to dto for second service
	public static ThirdServiceStudentDto transformToThirdServiceDto(Student student) {
		return ThirdServiceStudentDto.builder()
				.id(student.getId())
				.lastName(student.getLastName())
				.build();
	}
}