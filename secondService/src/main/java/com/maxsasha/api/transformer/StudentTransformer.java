package com.maxsasha.api.transformer;

import com.maxsasha.api.dto.StudentDto;
import com.maxsasha.entity.Student;

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

}