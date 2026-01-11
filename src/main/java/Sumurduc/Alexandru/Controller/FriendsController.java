package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Services.FriendsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @PostMapping("/{pid}/{fid}")
    public ResponseEntity<String> addFriend(@PathVariable Integer pid, @PathVariable Integer fid) {
        friendsService.addFriend(pid, fid);
        return ResponseEntity.status(HttpStatus.CREATED).body("Friendship added");
    }

    @DeleteMapping("/{pid}/{fid}")
    public ResponseEntity<?> deleteFriend(@PathVariable Integer pid, @PathVariable Integer fid) {
        boolean deleted = friendsService.deleteFriendship(pid, fid);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Friendship not found");
    }

    @GetMapping("/{playerID}")
    public List<Player> getFriends(@PathVariable Integer playerID) {
        return friendsService.getFriends(playerID);
    }
}
