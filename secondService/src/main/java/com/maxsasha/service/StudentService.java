package com.maxsasha.service;

import static com.maxsasha.api.transformer.StudentToThirdServiceTransformer.transformToThirdServiceDto;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;
import com.maxsasha.service.redirector.RedirectorToThirdService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RedirectorToThirdService redirect;

    public Student create(Student student) throws JsonProcessingException {
        Student createdStudent = redirect.redirectToCreate(transformToThirdServiceDto(student, student.getId()));
        createdStudent.setMiddleName(student.getMiddleName());
        studentRepository.updateDocumentMiddleName(createdStudent.getId(), student.getMiddleName());
        return createdStudent;
    }

    public Student update(Student student, String id) throws JsonProcessingException {
        Student updatedStudent = redirect.redirectToUpdate(transformToThirdServiceDto(student, student.getId()));
        updatedStudent.setMiddleName(student.getMiddleName());
        studentRepository.updateDocumentMiddleName(updatedStudent.getId(), student.getMiddleName());
        return updatedStudent;
    }
}