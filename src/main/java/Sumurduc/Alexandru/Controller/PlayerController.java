package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping
    public ResponseEntity<String> addPlayer(@Valid @RequestBody Player player) {
        playerService.addPlayer(player);
        return ResponseEntity.status(HttpStatus.CREATED).body("Player added");
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deletePlayer(@PathVariable String username) {
        boolean deleted = playerService.deleteByUsername(username);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
    }

    @PatchMapping("/{playerId}/add-money")
    public ResponseEntity<?> addMoney(
            @PathVariable Integer playerId,
            @RequestParam Float amount
    ) {
        boolean ok = playerService.addMoney(playerId, amount);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found");
    }

}
