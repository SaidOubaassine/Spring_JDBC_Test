package learn.spring.jdbc.dao.impl;

import learn.spring.jdbc.TestDataUtil;
import learn.spring.jdbc.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
