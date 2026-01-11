package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Game;
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
        String sql = """
            SELECT 
                game_id AS gameId,
                developer_id AS devId,
                title,
                tags,
                description,
                price,
                discount
            FROM Game
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Game.class));
    }

    public List<Game> findGameOfCompany(String devName) {
        String sql = """
            SELECT 
                g.game_id AS gameId,
                g.developer_id AS devId,
                g.title,
                g.tags,
                g.description,
                g.price,
                g.discount
            FROM Game g
            JOIN Developer d ON d.developer_id = g.developer_id
            WHERE d.username = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Game.class), devName);
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
    }

    public int delete(String title) {
        String sql = "DELETE FROM Game WHERE title = ?";
        return jdbcTemplate.update(sql, title);
    }
}
