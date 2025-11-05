package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Developer;
import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Repository.DeveloperRepository;
import Sumurduc.Alexandru.Repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    private final DeveloperRepository developerRepository;
    public DeveloperController(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    // GET  /api/players
    @GetMapping
    public List<Developer> getPlayers() {
        return developerRepository.findAll();
    }

    // POST /api/players
    @PostMapping
    public String addPlayer(@RequestBody Developer developer) {
        developerRepository.add(developer);
        return "Developer added";
    }

    @DeleteMapping("/{username}")
    public String deletePlayer(@RequestBody String username){
        developerRepository.delete(username);
        return "Developer Deleted";

    }
}
