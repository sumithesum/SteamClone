package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Developer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeveloperRepository {
    private final JdbcTemplate jdbcTemplate;

    public DeveloperRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Developer> findAll() {
        return jdbcTemplate.query("SELECT * FROM Developer",
                new BeanPropertyRowMapper<>(Developer.class));
    }


    public void add(Developer developer) {
        String sql = """
                INSERT INTO Developer (username, email, phone, password, bank, studio_name)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                developer.getUsername(),
                developer.getEmail(),
                developer.getPhone(),
                developer.getPassword(),
                developer.getBank(),
                developer.getStudio_name()
        );

        System.out.println("Developer added: " + developer.getUsername());
    }

    public void delete(String username) {

        String sql = "DELETE FROM DEVELOPER WHERE username = ?";
        jdbcTemplate.update(sql, username);

        System.out.println("Developer deleted: " + username);
    }
}
