package learn.spring.jdbc.dao;

import learn.spring.jdbc.domain.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    void create(Student student);
    Optional<Student> findOne(int id);
    List<Student> find();
    void update(int id, Student student);
    void delete(int id);
}
