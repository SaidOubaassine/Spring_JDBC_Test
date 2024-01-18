package learn.spring.jdbc.dao.impl;

import learn.spring.jdbc.TestDataUtil;
import learn.spring.jdbc.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class StudentDaoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private StudentDaoImpl underTest;
    @Test
    public void testThatCreateStudentGeneratesCorrectSql(){
        Student student = TestDataUtil.createTestStudentA();
        underTest.create(student);
        verify(jdbcTemplate).update(
                eq("INSERT INTO students (id, name, country, age) VALUES (?, ?, ?, ?)"),
                eq(1),
                eq("Said"),
                eq("Maroc"),
                eq(25)
        );
    }
}
