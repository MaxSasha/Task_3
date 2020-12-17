package com.maxsasha.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxsasha.api.dto.SecondServiceStudentDto;
import com.maxsasha.api.dto.StudentDto;
import com.maxsasha.entity.Student;

public class StudentTransformer {

	public static StudentDto transform(Student student) {
		return StudentDto.builder()
				.id(student.getId())
				.firstName(student.getFisrtName())
				.midleName(student.getMidleName())
				.lastName(student.getLastName())
				.build();
	}

	public static Student transform(StudentDto dto) {
		return Student.builder()
				.id(dto.getId())
				.fisrtName(dto.getFirstName())
				.midleName(dto.getMidleName())
				.lastName(dto.getLastName())
				.build();
	}

	public static List<StudentDto> transform(List<Student> students) {
		return students.stream()
				.map(u -> transform(u))
				.collect(Collectors.toList());
	}

	// convert to dto for second service
	public static SecondServiceStudentDto transformToSecondServiceDto(Student student) {
		return SecondServiceStudentDto.builder()
				.id(student.getId())
				.midleName(student.getMidleName())
				.lastName(student.getLastName())
				.build();
	}

	// transform to json for restTemplate
	public static String transformToJson(SecondServiceStudentDto student) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(student);
	}
}