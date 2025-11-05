package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Developer;
import Sumurduc.Alexandru.Model.Game;
import Sumurduc.Alexandru.Repository.DeveloperRepository;
import Sumurduc.Alexandru.Repository.GameRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/games")
public class GameController {
    private final GameRepository gameRepository;

    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // GET  /api/players
    @GetMapping
    public List<Game> getPlayers() {
        return gameRepository.findAll();
    }

    // POST /api/players
    @PostMapping
    public String addPlayer(@RequestBody Game game) {
        gameRepository.add(game);
        return "Game added";
    }

    @DeleteMapping("/{title}")
    public String deletePlayer(@RequestBody String title){
        gameRepository.delete(title);
        return "Game Deleted";

    }
}
