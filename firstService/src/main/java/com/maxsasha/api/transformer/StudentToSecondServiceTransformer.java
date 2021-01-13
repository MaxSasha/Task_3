package com.maxsasha.api.transformer;

import com.maxsasha.api.dto.SecondServiceStudentDto;
import com.maxsasha.entity.Student;

public class StudentToSecondServiceTransformer {
    public static SecondServiceStudentDto transformToSecondServiceDto(Student student,String id) {
        return SecondServiceStudentDto.builder()
                .id(id)
                .middleName(student.getMiddleName())
                .lastName(student.getLastName())
                .build();
    }
}