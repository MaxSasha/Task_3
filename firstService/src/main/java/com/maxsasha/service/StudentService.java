package com.maxsasha.service;

import static com.maxsasha.api.transformer.StudentToSecondServiceTransformer.transformToSecondServiceDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;
import com.maxsasha.service.redirector.RedirectorToSecondService;

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
        createdStudent.setFirstName(student.getFirstName());
        studentRepository.updateDocumentFirstName(createdStudent.getId(), createdStudent.getFirstName());
        return createdStudent;
    }

    public Student update(Student student, String id) throws JsonProcessingException {
        Student updatedStudent = redirect.redirectToUpdate(transformToSecondServiceDto(student, id));
        updatedStudent.setFirstName(student.getFirstName());
        studentRepository.updateDocumentFirstName(updatedStudent.getId(), updatedStudent.getFirstName());
        return updatedStudent;
    }

    public void delete(String id) {
        studentRepository.deleteById(id);
    }
}