package Sumurduc.Alexandru.Repository;


import Sumurduc.Alexandru.Model.Player;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Player> findAll() {
        return jdbcTemplate.query("SELECT * FROM player",
                new BeanPropertyRowMapper<>(Player.class));
    }


    public void add(Player player) {
        jdbcTemplate.update("""
            INSERT INTO player (username, email, phone, password, bank, private)
            VALUES (?, ?, ?, ?, ?, ?)
        """, player.getUsername(), player.getEmail(), player.getPhone(),
                player.getPassword(), player.getBank(), player.getPrivatef());
    }
}
