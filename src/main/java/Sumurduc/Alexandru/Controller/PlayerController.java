package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Repository.PlayerRepository;
import Sumurduc.Alexandru.Model.Player;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {
    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // GET /api/players
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
}