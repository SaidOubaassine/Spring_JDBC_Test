package learn.spring.jdbc.dao.impl;

import learn.spring.jdbc.TestDataUtil;
import learn.spring.jdbc.domain.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
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
    @Test
    public void testThatFindOneStudentGeneratesCorrectSql(){
        underTest.findOne(1);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, country, age FROM students WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<StudentDaoImpl.StudentRowMapper>any(),
                eq(1)
        );
    }
    @Test
    public void testThatFindStudentsGeneratesCorrectSql(){
        underTest.find();
        verify(jdbcTemplate).query(
                eq("SELECT id, name, country, age FROM students"),
                ArgumentMatchers.<StudentDaoImpl.StudentRowMapper>any()
        );
    }
    @Test
    public void testThatUpdateStudentGeneratesCorrectSql(){
        Student student = TestDataUtil.createTestStudentB();
        underTest.update(1, student);
        verify(jdbcTemplate).update(
                eq("UPDATE students SET id = ?, name = ?,  country = ?, age = ? WHERE id = ?"),
                eq(2),
                eq("Mohamed"),
                eq("France"),
                eq(22),
                eq(1)
        );
    }
    @Test
    public void testThatDeleteStudentGenerateCorrectSql(){
        underTest.delete(1);
        verify(jdbcTemplate).update(
                eq("DELETE FROM students WHERE id = ?"),
                eq(1)
        );
    }
}
