package learn.spring.jdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    private int id;
    private String name;
    private String country;
    private int age;
}
