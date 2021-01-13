package com.maxsasha.api.transformer;

import com.maxsasha.api.dto.ThirdServiceStudentDto;
import com.maxsasha.entity.Student;

public class StudentToThirdServiceTransformer {
    public static ThirdServiceStudentDto transformToThirdServiceDto(Student student,String id) {
        return ThirdServiceStudentDto.builder()
                .id(id)
                .lastName(student.getLastName())
                .build();
    }
}