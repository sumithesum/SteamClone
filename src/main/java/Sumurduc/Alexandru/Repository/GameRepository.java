package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Game;
import Sumurduc.Alexandru.Model.Player;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {

    private final JdbcTemplate jdbcTemplate;

    public GameRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Game> findAll() {
        return jdbcTemplate.query("SELECT * FROM Game",
                new BeanPropertyRowMapper<>(Game.class));
    }

    public List<Game> findGameOfCompany(String devName) {
        return jdbcTemplate.query("SELECT * FROM Game",
                new BeanPropertyRowMapper<>(Game.class));
    }
    public void add(Game game) {
        String sql = """
            INSERT INTO Game (developer_id, title, tags, description, price, discount)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        jdbcTemplate.update(
                sql,
                game.getDevId(),
                game.getTitle(),
                game.getTags(),
                game.getDescription(),
                game.getPrice(),
                game.getDiscount()
        );

        System.out.println("Game added: " + game.getTitle());
    }

    public void delete(String title) {

        String sql = "DELETE FROM Game WHERE Title = ?";
        jdbcTemplate.update(sql, title);
        int rows = jdbcTemplate.update(sql, title);

    }


}
