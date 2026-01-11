package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Library;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibraryRepository {

    private final JdbcTemplate jdbcTemplate;

    public LibraryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int purchase(Integer playerId, Integer gameId) {
        String sql = """
            INSERT INTO Library (player_id, game_id, hours_played)
            VALUES (?, ?, 0.00)
        """;
        return jdbcTemplate.update(sql, playerId, gameId);
    }

    public List<Library> findByPlayerId(Integer playerId) {
        String sql = """
            SELECT
                player_id AS playerId,
                game_id AS gameId,
                hours_played AS hoursPlayed,
                acquired_at AS acquiredAt
            FROM Library
            WHERE player_id = ?
            ORDER BY acquired_at DESC
        """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Library.class), playerId);
    }

    public int updateHours(Integer playerId, Integer gameId, Float hoursPlayed) {
        String sql = """
            UPDATE Library
            SET hours_played = ?
            WHERE player_id = ? AND game_id = ?
        """;
        return jdbcTemplate.update(sql, hoursPlayed, playerId, gameId);
    }
}
