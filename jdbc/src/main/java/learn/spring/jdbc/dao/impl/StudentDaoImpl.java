package learn.spring.jdbc.dao.impl;

import learn.spring.jdbc.dao.StudentDao;
import learn.spring.jdbc.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StudentDaoImpl implements StudentDao {
    private final JdbcTemplate jdbcTemplate;
    public StudentDaoImpl(final JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void create(Student student) {
        jdbcTemplate.update(
                "INSERT INTO students (id, name, country, age) VALUES (?, ?, ?, ?)",
                student.getId(),
                student.getName(),
                student.getCountry(),
                student.getAge()
        );
    }
}
