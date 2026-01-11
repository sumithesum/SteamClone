package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Comment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentsRepository {

    private final JdbcTemplate jdbcTemplate;

    public CommentsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int upsert(Comment c) {
        String sql = """
            INSERT INTO Comments (player_id, game_id, comment, vote)
            VALUES (?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE
                comment = VALUES(comment),
                vote = VALUES(vote),
                created_at = CURRENT_TIMESTAMP
        """;

        return jdbcTemplate.update(sql, c.getPlayerId(), c.getGameId(), c.getComment(), c.getVote());
    }

    public Double getAverageVoteByGameId(Integer gameId) {
        String sql = "SELECT AVG(vote) FROM Comments WHERE game_id = ?";
        Double avg = jdbcTemplate.queryForObject(sql, Double.class, gameId);
        return avg;
    }

    public List<Comment> findByGameId(Integer gameId) {
        String sql = """
            SELECT
                player_id AS playerId,
                game_id AS gameId,
                comment,
                vote,
                created_at AS createdAt
            FROM Comments
            WHERE game_id = ?
            ORDER BY created_at DESC
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Comment.class), gameId);
    }

    public int delete(Integer playerId, Integer gameId) {
        String sql = "DELETE FROM Comments WHERE player_id = ? AND game_id = ?";
        return jdbcTemplate.update(sql, playerId, gameId);
    }
}
