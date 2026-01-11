package Sumurduc.Alexandru.Repository;

import Sumurduc.Alexandru.Model.Player;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FriendsRepository {

    private final JdbcTemplate jdbcTemplate;

    public FriendsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addFriend(Integer pid, Integer fid) {
        // bidirectional friendship (A->B and B->A)
        String sql = "INSERT INTO Friends (player_id, friend_id) VALUES (?, ?)";
        int r1 = jdbcTemplate.update(sql, pid, fid);
        int r2 = jdbcTemplate.update(sql, fid, pid);
        return r1 + r2;
    }

    public int destroyFriendShip(Integer pid, Integer fid) {
        String sql = "DELETE FROM Friends WHERE player_id = ? AND friend_id = ?";
        int r1 = jdbcTemplate.update(sql, pid, fid);
        int r2 = jdbcTemplate.update(sql, fid, pid);
        return r1 + r2;
    }

    public int deleteAllFriendShips(Integer pid) {
        String sql = "DELETE FROM Friends WHERE player_id = ?";
        return jdbcTemplate.update(sql, pid);
    }

    public List<Player> findFriendsOfPlayer(Integer playerId) {
        // mapare corectă: player_id -> id, private -> privatef
        String sql = """
            SELECT
                p.player_id AS id,
                p.username,
                p.email,
                p.phone,
                p.password,
                p.bank,
                p.private AS privatef
            FROM Friends f
            JOIN Player p ON p.player_id = f.friend_id
            WHERE f.player_id = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class), playerId);
    }
}
