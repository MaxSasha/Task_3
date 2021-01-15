package com.maxsasha.services.currentService.service;

import static com.maxsasha.services.currentService.api.transformer.StudentTransformer.transform;
import static com.maxsasha.services.second.transformer.StudentTransformer.transformToSecondServiceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.services.currentService.db.repository.StudentRepository;
import com.maxsasha.services.currentService.entity.Student;
import com.maxsasha.services.second.redirector.RedirectorToSecondService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final RedirectorToSecondService redirect;

    public Page<Student> getUsers(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Student create(Student student) throws JsonProcessingException {
        Student createdStudent = redirect.redirectToCreate(transformToSecondServiceDto(student, student.getId()));
        studentRepository.updateDocumentFirstName(createdStudent.getId(), student.getFirstName());
        return transform(createdStudent, student.getFirstName());
    }

    public Student update(Student student, String id) throws JsonProcessingException {
        Student updatedStudent = redirect.redirectToUpdate(transformToSecondServiceDto(student, id));
        studentRepository.updateDocumentFirstName(updatedStudent.getId(), student.getFirstName());
        return transform(updatedStudent, student.getFirstName());
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}