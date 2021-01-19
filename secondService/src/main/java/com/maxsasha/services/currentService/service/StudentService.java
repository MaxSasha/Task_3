package com.maxsasha.services.currentService.service;

import static com.maxsasha.services.currentService.api.transformer.StudentTransformer.transform;
import static com.maxsasha.services.thirdService.transformer.StudentTransformer.transformToThirdServiceDto;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.services.currentService.db.repository.StudentRepository;
import com.maxsasha.services.currentService.entity.Student;
import com.maxsasha.services.thirdService.redirector.RedirectorToThirdService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RedirectorToThirdService redirect;

    public Student create(Student student) throws JsonProcessingException {
        Student createdStudent = redirect.redirectToCreate(transformToThirdServiceDto(student, student.getId()));
        studentRepository.updateDocumentMiddleName(createdStudent.getId(), student.getMiddleName());
        return transform(createdStudent, student.getMiddleName());
    }

    public Student update(Student student, String id) throws JsonProcessingException {
        Student updatedStudent = redirect.redirectToUpdate(id, transformToThirdServiceDto(student, student.getId()));
        studentRepository.updateDocumentMiddleName(updatedStudent.getId(), student.getMiddleName());
        return transform(updatedStudent, student.getMiddleName());
    }
}