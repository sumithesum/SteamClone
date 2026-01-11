package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Game;
import Sumurduc.Alexandru.Services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/developer/{username}")
    public List<Game> getGamesOfDeveloper(@PathVariable String username) {
        return gameService.getGamesOfDeveloper(username);
    }

    @PostMapping
    public ResponseEntity<String> addGame(@Valid @RequestBody Game game) {
        gameService.addGame(game);
        return ResponseEntity.status(HttpStatus.CREATED).body("Game added");
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<String> deleteGame(@PathVariable String title) {
        boolean deleted = gameService.deleteByTitle(title);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found");
    }
}
