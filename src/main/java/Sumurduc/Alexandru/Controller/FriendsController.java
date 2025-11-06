package Sumurduc.Alexandru.Controller;


import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Repository.FriendsRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsRepository friendsRepository;

    public FriendsController(FriendsRepository friendsRepository) {
        this.friendsRepository = friendsRepository;
    }

    @GetMapping("/{playerID}")
    public List<Player> getPlayers(@PathVariable("playerID") Integer playerID) {
        return friendsRepository.findAllFriends(playerID);
    }


    @PostMapping("/{pid}/{fid}")
    public String addFriend(@PathVariable("pid") Integer pid, @PathVariable("fid") Integer fid) {
        friendsRepository.addFriend(pid,fid);
        return "Friendship added";
    }

    @DeleteMapping("/{pid}/{fid}")
    public String deletePlayer(@PathVariable("pid") Integer pid, @PathVariable("fid") Integer fid){
        friendsRepository.destroyFriendShip(pid,fid);
        return "Friendship Deleted";

    }
}
