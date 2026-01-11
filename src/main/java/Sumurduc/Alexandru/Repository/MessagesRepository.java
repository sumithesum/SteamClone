package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Message;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessagesRepository {

    private final JdbcTemplate jdbcTemplate;

    public MessagesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int send(Message msg) {
        String sql = """
            INSERT INTO Messages (sender_id, receiver_id, game_id, message, is_read)
            VALUES (?, ?, ?, ?, FALSE)
        """;

        return jdbcTemplate.update(
                sql,
                msg.getSenderId(),
                msg.getReceiverId(),
                msg.getGameId(),      // poate fi null
                msg.getMessage()
        );
    }

    public List<Message> inbox(Integer receiverId) {

        String sql = """
            SELECT
                message_id AS messageId,
                sender_id AS senderId,
                receiver_id AS receiverId,
                game_id AS gameId,
                message,
                sent_at AS sentAt,
                is_read AS isRead
            FROM Messages
            WHERE receiver_id = ?
            ORDER BY sent_at DESC
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Message.class), receiverId);
    }

    public int markAsRead(Integer messageId) {
        String sql = "UPDATE Messages SET is_read = TRUE WHERE message_id = ?";
        return jdbcTemplate.update(sql, messageId);
    }
}
