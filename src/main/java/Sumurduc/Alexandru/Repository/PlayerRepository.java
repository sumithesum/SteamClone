package Sumurduc.Alexandru.Repository;


import Sumurduc.Alexandru.Model.Player;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public void delete(String username) {

        String sql = "DELETE FROM player WHERE username = ?";
        jdbcTemplate.update(sql, username);
        int rows = jdbcTemplate.update(sql, username);

    }

//    public Integer update(String username,Integer pozition){
//        String sql = "SELECT * FROM player WHERE username = ?";
//
//
//        try {
//            return jdbcTemplate.queryForObject(
//                    sql,
//                    new BeanPropertyRowMapper<>(Player.class),
//                    username
//            );
//        } catch (EmptyResultDataAccessException e) {
//            // dacă nu există niciun jucător cu acel username, întoarce null
//            return 0;
//        }
//    }
}
