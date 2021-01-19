package com.maxsasha.services.currentService.api.transformer;

import com.maxsasha.services.currentService.api.dto.StudentDto;
import com.maxsasha.services.currentService.entity.Student;

public class StudentTransformer {

    public static StudentDto transform(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .build();
    }

    public static Student transform(StudentDto dto) {
        return Student.builder()
                .id(dto.getId())
                .middleName(dto.getMiddleName())
                .lastName(dto.getLastName())
                .build();
    }
    public static Student transform(Student student, String middleName) {
        return Student.builder()
                .id(student.getId())
                .middleName(middleName)
                .lastName(student.getLastName())
                .build();
    }
}