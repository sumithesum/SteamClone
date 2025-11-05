package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Repository.PlayerRepository;
import Sumurduc.Alexandru.Model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // GET  /api/players
    @GetMapping
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    // POST /api/players
    @PostMapping
    public String addPlayer(@RequestBody Player player) {
        playerRepository.add(player);
        return "Player added";
    }

    @DeleteMapping("/{username}")
    public String deletePlayer(@RequestBody String username){
        playerRepository.delete(username);
        return "Player Deleted";

    }

//    @PutMapping
//    public String updatePlayer(@RequestBody String username,Integer category){
//        return "Player updated";
//    }
}