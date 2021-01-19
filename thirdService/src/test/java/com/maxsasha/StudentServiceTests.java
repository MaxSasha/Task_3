package com.maxsasha;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maxsasha.api.transformer.StudentTransformer;
import com.maxsasha.db.repository.StudentRepository;
import com.maxsasha.entity.Student;
import com.maxsasha.service.StudentService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTests {
    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepoMock;
    
    @Mock 
    private StudentTransformer studentTransformer;
    
    private Student student;
    private Student studentAnother;
    private static final String id = "123321";
    @BeforeEach
    private void populateStudentFields() {
        student = Student.builder()
                .id(id)
                .lastName("zxc")
                .build(); 
        
        studentAnother = Student.builder()
                .lastName("vbn")
                .build();
    }

    @Test
    void createUser_returnsCreatedUser() throws JsonProcessingException {
        when(studentRepoMock.insert(student)).thenReturn(student);
        assertThat(studentService.create(student)).isEqualTo(student);
    }

    @Test
    void createUser_returnsCreatedUserIsNotEqual() throws JsonProcessingException {
        when(studentRepoMock.insert(student)).thenReturn(student);
        assertThat(studentService.create(student)).isNotEqualTo(studentAnother);
    }

    @Test
    void createStudent_checkName() throws JsonProcessingException {
        when(studentRepoMock.insert(student)).thenReturn(student);
        assertThat(studentService.create(student).getLastName())
                                                 .startsWith("z")
                                                 .endsWith("c")
                                                 .isEqualToIgnoringCase("ZXC");
    }
    
    @Test
    void updateStudent_checkEqualToAnother() throws JsonProcessingException {
        when(studentRepoMock.save(student)).thenReturn(student);
        assertThat(studentService.edit(student, id).getLastName())
                                                     .as("check %s's lastName", student.getLastName())
                                                     .isEqualTo("zxc");
    }
}