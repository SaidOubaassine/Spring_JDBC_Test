package learn.spring.jdbc.dao.impl;

import learn.spring.jdbc.TestDataUtil;
import learn.spring.jdbc.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class StudentDaoImplIntegrationTests {
    private StudentDaoImpl underTest;
    @Autowired
    public StudentDaoImplIntegrationTests(StudentDaoImpl underTest){
        this.underTest = underTest;
    }
    @Test
    public void testThatStudentCanBeCreatedAndRecalled(){
        Student student = TestDataUtil.createTestStudentA();
        underTest.create(student);
        Optional<Student> result = underTest.findOne(student.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(student);
    }
    @Test
    public void testThatMultipleStudentsCanBeCreatedAndRecalled(){
        Student studentA = TestDataUtil.createTestStudentA();
        underTest.create(studentA);
        Student studentB = TestDataUtil.createTestStudentB();
        underTest.create(studentB);
        Student studentC = TestDataUtil.createTestStudentC();
        underTest.create(studentC);

        List<Student> result = underTest.find();
        assertThat(result)
                .hasSize(3)
                .containsExactly(studentA, studentB, studentC);
    }
    @Test
    public void testThatStudentCanBeUpdated(){
        Student studentA = TestDataUtil.createTestStudentA();
        studentA.setName("Kamal");
        underTest.create(studentA);
        underTest.update(studentA.getId(), studentA);
        Optional<Student> result = underTest.findOne(studentA.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(studentA);
    }
}
