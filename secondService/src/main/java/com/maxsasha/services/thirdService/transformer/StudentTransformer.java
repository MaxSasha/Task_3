package com.maxsasha.services.thirdService.transformer;

import com.maxsasha.services.thirdService.dto.StudentDto;
import com.maxsasha.services.currentService.entity.Student;

public class StudentTransformer {
    public static StudentDto transformToThirdServiceDto(Student student,String id) {
        return StudentDto.builder()
                .id(id)
                .lastName(student.getLastName())
                .build();
    }
}