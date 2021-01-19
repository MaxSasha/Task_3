package com.maxsasha;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.services.currentService.entity.Student;
import com.maxsasha.services.currentService.service.StudentService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTests {
    @Mock
    private StudentService studentMock;

    private Student student;
    private Student studentBad;
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
        
        studentList = Arrays.asList(student, studentAnother);
        page = new PageImpl<Student>(studentList, PageRequest.of(0, 4), studentList.size());
    }

    @Test
    void createUser_returnsCreatedUser() throws JsonProcessingException {
        when(studentMock.create(student)).thenReturn(student);
        assertThat(studentMock.create(student)).isEqualTo(student);
    }

    @Test
    void createUser_returnsCreatedUserIsNotEqual() throws JsonProcessingException {
        when(studentMock.create(student)).thenReturn(student);
        assertThat(studentMock.create(student)).isNotEqualTo(studentAnother);
    }

    @Test
    void createStudent_checkName() throws JsonProcessingException {
        when(studentMock.create(student)).thenReturn(student);
        assertThat(studentMock.create(student).getFirstName()).startsWith("q").endsWith("e")
                .isEqualToIgnoringCase("QWAE");
    }

    @Test
    void getStudents_checkPageSizeAndContainsStudents() {
        when(studentMock.getUsers(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentMock.getUsers(PageRequest.of(0, 4)).getContent()).hasSize(2)
                                                                           .contains(student, studentAnother)
                                                                           .doesNotContain(studentBad);
    } 
    
    @Test
    void updateStudent_checkEqualToAnother() throws JsonProcessingException {
        when(studentMock.update(student, id)).thenReturn(student);
        assertThat(studentMock.update(student, id).getFirstName())
                                                  .as("check %s's firstName", student.getFirstName())
                                                  .isEqualTo("qwae");
    }

    @Test
    void createStudent_checkToThrowException() throws JsonProcessingException {
        when(studentMock.create(student)).thenThrow(new JsonProcessingException("Error with map json to String"){});
        assertThatThrownBy(() -> { studentMock.create(student); }).hasMessage("Error with map json to String");
    }

    @Test
    void getStudents_checkDoNotContainStudent() {
        when(studentMock.getUsers(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentMock.getUsers(PageRequest.of(0, 4)).getContent()).extracting(Student::getFirstName)
                                                                           .doesNotContain("gfd", "sdaf");
    }   
    
    @Test
    void getStudents_checkMultimpeContains() {
        when(studentMock.getUsers(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentMock.getUsers(PageRequest.of(0, 4)).getContent()).extracting("firstName", "middleName")
                                                                           .contains(tuple("qwae", "asd"),
                                                                                     tuple("das", "asd"));
    }
    
    @Test
    void getStudents_checkContainsCharacterInFirstName() {
        when(studentMock.getUsers(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentMock.getUsers(PageRequest.of(0, 4)).getContent()).filteredOn(character -> character.getFirstName().contains("a"))
                                                                           .containsOnly(student, studentAnother);
    }
    
    @Test
    void getStudents_checkContainsCharacterInLastName() {
        when(studentMock.getUsers(PageRequest.of(0, 4))).thenReturn(page);
        assertThat(studentMock.getUsers(PageRequest.of(0, 4)).getContent()).filteredOn(character -> character.getFirstName().contains("a"))
                                                                                   .containsOnly(student, studentAnother)
                                                                                   .extracting(character -> character.getFirstName())
                                                                                   .contains("qwae","das");
    }  
}