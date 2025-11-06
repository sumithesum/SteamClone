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


    public List<Player> findAllFriends (Integer playerID){
        String sql = """
                SELECT p.*
                FROM friends f , player p
                WHERE f.friend_id = p.player_id AND f.player_id = """ + playerID;
        return this.jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Player.class));
    }

    public void addFriend(Integer pid,Integer fid){
        String sql = "INSERT INTO FRIENDS (player_id,friend_id) VALUES (?,?)";
        jdbcTemplate.update(sql,pid,fid);
        jdbcTemplate.update(sql,fid,pid);


    }

    public void destroyFriendShip(Integer pid , Integer fid){
        String sql = "DELETE FROM FRIENDS WHERE player_id = ? AND friend_id = ?";
        jdbcTemplate.update(sql,pid,fid);
        jdbcTemplate.update(sql,fid,pid);
    }

    public void  deleteAllFriendShips(Integer pid){
        String sql = "DELETE FROM FRIENDS WHERE player_id = ?";
        jdbcTemplate.update(sql,pid);
    }

}
