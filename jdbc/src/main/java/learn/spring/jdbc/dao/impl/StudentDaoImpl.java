package learn.spring.jdbc.dao.impl;

import learn.spring.jdbc.dao.StudentDao;
import learn.spring.jdbc.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Student> findOne(int id){
        List<Student> results = jdbcTemplate.query(
                "SELECT id, name, country, age FROM students WHERE id = ? LIMIT 1",
                new StudentRowMapper(), id);
        return results.stream().findFirst();
    }
    public static class StudentRowMapper implements RowMapper<Student>{
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException{
            return Student.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .country(rs.getString("country"))
                    .age(rs.getInt("age"))
                    .build();
        }
    }
    @Override
    public List<Student> find(){
        return jdbcTemplate.query(
                "SELECT id, name, country, age FROM students",
                new StudentRowMapper()
        );
    }

    @Override
    public void update(int id, Student student) {
        jdbcTemplate.update(
                "UPDATE students SET id = ?, name = ?,  country = ?, age = ? WHERE id = ?",
                student.getId(),
                student.getName(),
                student.getCountry(),
                student.getAge(),
                id
        );
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(
                "DROP FROM students WHERE id = ?",
                id
        );
    }
}
