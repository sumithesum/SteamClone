package Sumurduc.Alexandru.Repository;


import Sumurduc.Alexandru.Model.Player;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerRepository {
    private final JdbcTemplate jdbcTemplate;

    public PlayerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Player> findAll() {
        String sql = """
        SELECT
            player_id AS id,
            username,
            email,
            phone,
            password,
            bank,
            private AS privatef
        FROM Player
    """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class));
    }



    public void add(Player player) {
        jdbcTemplate.update("""
            INSERT INTO player (username, email, phone, password, bank, private)
            VALUES (?, ?, ?, ?, ?, ?)
        """, player.getUsername(), player.getEmail(), player.getPhone(),
                player.getPassword(), player.getBank(), player.getPrivatef());
    }

    public int deleteByUsername(String username) {
        String sql = "DELETE FROM Player WHERE username = ?";
        return jdbcTemplate.update(sql, username);
    }

    public void delete(String username) {

        String sql = "DELETE FROM player WHERE username = ?";
        jdbcTemplate.update(sql, username);
        int rows = jdbcTemplate.update(sql, username);

    }

    public Integer getId(String username){
        String sql = "Select player_id from player where username = ?" ;

        return jdbcTemplate.queryForObject(sql,Integer.class,username);
    }

    public int addMoney(Integer playerId, Float amount) {
        String sql = "UPDATE Player SET bank = bank + ? WHERE player_id = ?";
        return jdbcTemplate.update(sql, amount, playerId);
    }
    public int subtractMoneyIfEnough(Integer playerId, Float amount) {
        String sql = """
        UPDATE Player
        SET bank = bank - ?
        WHERE player_id = ? AND bank >= ?
    """;
        return jdbcTemplate.update(sql, amount, playerId, amount);
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
//
//            return 0;
//        }
//    }
}
