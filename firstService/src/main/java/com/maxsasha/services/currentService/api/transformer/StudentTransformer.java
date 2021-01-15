package com.maxsasha.services.currentService.api.transformer;

import java.util.List;
import java.util.stream.Collectors;

import com.maxsasha.services.currentService.api.dto.StudentDto;
import com.maxsasha.services.currentService.entity.Student;

public class StudentTransformer {

    public static StudentDto transform(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .build();
    }

    public static Student transform(StudentDto dto) {
        return Student.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .build();
    }
    public static Student transform(Student student, String firstName) { 
        return Student.builder()
                .id(student.getId())
                .firstName(firstName)
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .build();
    }

    public static List<StudentDto> transform(List<Student> students) {
        return students.stream()
                .map(u -> transform(u))
                .collect(Collectors.toList());
    }
}