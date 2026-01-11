package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Repository.FriendsRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {

    private final FriendsRepository friendsRepository;

    public FriendsService(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    public void addFriend(Integer pid, Integer fid) {
        if (pid.equals(fid)) {
            throw new IllegalArgumentException("You cannot add yourself as friend.");
        }
        try {
            friendsRepository.addFriend(pid, fid);
        } catch (DuplicateKeyException ex) {
            throw new IllegalArgumentException("Friendship already exists.");
        }
    }

    public boolean deleteFriendship(Integer pid, Integer fid) {
        return friendsRepository.destroyFriendShip(pid, fid) > 0;
    }

    public List<Player> getFriends(Integer playerId) {
        return friendsRepository.findFriendsOfPlayer(playerId);
    }
}
