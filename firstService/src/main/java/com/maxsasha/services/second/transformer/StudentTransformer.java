package com.maxsasha.services.second.transformer;

import com.maxsasha.services.second.dto.StudentDto;
import com.maxsasha.services.currentService.entity.Student;

public class StudentTransformer {
    public static StudentDto transformToSecondServiceDto(Student student,String id) {
        return StudentDto.builder()
                .id(id)
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .build();
    }
}