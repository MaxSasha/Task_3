package com.maxsasha;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.services.currentService.api.transformer.StudentTransformer;
import com.maxsasha.services.currentService.db.repository.StudentRepository;
import com.maxsasha.services.currentService.entity.Student;
import com.maxsasha.services.currentService.service.StudentService;
import com.maxsasha.services.second.dto.StudentDto;
import com.maxsasha.services.second.redirector.RedirectorToSecondService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTests {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepoMock;
    
    @Mock
    private RedirectorToSecondService redirector;
    
    @Mock 
    private StudentTransformer studentTransformer;
    
    private Student student;
    private Student studentBad;
    private StudentDto studentDto;
    private Student studentAnother;
    private List<Student> studentList;
    private Page<Student> page;
    private static final String id = "123321";
    private static final String firstName = "qwae";
    private static final String middleName = "asd";
    private static final String lastName = "zxc";

    @BeforeEach
    private void populateStudentFields() {
        student = Student.builder()
                .id(id)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .build();
        
        studentBad = Student.builder()
                .middleName(middleName)
                .build();
        
        studentAnother = Student.builder()
                .firstName("das")
                .middleName(middleName)
                .lastName("vbn")
                .build();
        studentDto = StudentDto.builder()
                .id(id)
                .middleName(middleName)
                .lastName(lastName)
                .build();
        
        studentList = Arrays.asList(student, studentAnother);
        page = new PageImpl<Student>(studentList, PageRequest.of(0, 4), studentList.size());
    }

    @Test
    void getStudents_checkPageSizeAndContainsStudents() {
        when(studentRepoMock.findAll(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentService.getUsers(PageRequest.of(0, 4)).getContent()).hasSize(2)
                                                                              .contains(student, studentAnother)
                                                                              .doesNotContain(studentBad);
    } 

    @Test
    void getStudents_checkDoNotContainStudent() {
        when(studentRepoMock.findAll(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentService.getUsers(PageRequest.of(0, 4)).getContent())
                                                                .extracting(Student::getFirstName)
                                                                .doesNotContain("gfd", "sdaf");
    }   
    
    @Test
    void getStudents_checkMultimpeContains() {
        when(studentRepoMock.findAll(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentService.getUsers(PageRequest.of(0, 4)).getContent()).extracting("firstName", "middleName")
                                                                              .contains(tuple("qwae", "asd"),
                                                                                        tuple("das", "asd"));
    }
    
    @Test
    void getStudents_checkContainsCharacterInFirstName() {
        when(studentRepoMock.findAll(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentService.getUsers(PageRequest.of(0, 4)).getContent()).filteredOn(character -> character.getFirstName()
                                                                              .contains("a"))
                                                                              .containsOnly(student, studentAnother);
    }
    
    @Test
    void getStudents_checkContainsCharacterInLastName() {
        when(studentRepoMock.findAll(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentService.getUsers(PageRequest.of(0, 4)).getContent()).filteredOn(character -> character.getFirstName()
                                                                              .contains("a"))
                                                                              .containsOnly(student, studentAnother)
                                                                              .extracting(character -> character.getFirstName())
                                                                              .contains("qwae","das");
    }  

    @Test
    void createStudent_checkToThrowException() throws JsonProcessingException  {
        when(studentRepoMock.exceptionMaker()).thenThrow(new RuntimeException("Error"){});
        assertThatThrownBy(() -> { studentService.exceptionMaker(); }).hasMessage("Error");
    }
}