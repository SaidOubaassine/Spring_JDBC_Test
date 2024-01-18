package learn.spring.jdbc;

import learn.spring.jdbc.dao.impl.StudentDaoImpl;
import learn.spring.jdbc.domain.Student;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@Log
public class JdbcApplication implements CommandLineRunner{
	private final DataSource dataSource;
	public JdbcApplication(final DataSource dataSource){
		this.dataSource = dataSource;
	}
	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

	@Override
	public void run(final String... args){
		final JdbcTemplate restTemplate = new JdbcTemplate(dataSource);
		StudentDaoImpl studentDaoIpml = new StudentDaoImpl(restTemplate);
		Student student = new Student(1, "Said", "Maroc", 25);
		studentDaoIpml.create(student);
		System.out.println(studentDaoIpml.findOne(1));
	}
}
