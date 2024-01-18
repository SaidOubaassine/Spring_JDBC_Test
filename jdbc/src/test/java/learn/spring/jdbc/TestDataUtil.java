package learn.spring.jdbc;

import learn.spring.jdbc.domain.Student;

public class TestDataUtil {
    private TestDataUtil(){

    }
    public static Student createTestStudentA(){
        return Student.builder()
                .id(1)
                .name("Said")
                .country("Maroc")
                .age(25)
                .build();
    }
    public static Student createTestStudentB(){
        return Student.builder()
                .id(1)
                .name("Mohamed")
                .country("France")
                .age(22)
                .build();
    }
}
