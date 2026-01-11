package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Developer;
import Sumurduc.Alexandru.Services.DeveloperService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping
    public List<Developer> getDevelopers() {
        return developerService.getAllDevelopers();
    }

    @PostMapping
    public ResponseEntity<String> addDeveloper(@Valid @RequestBody Developer developer) {
        developerService.addDeveloper(developer);
        return ResponseEntity.status(HttpStatus.CREATED).body("Developer added");
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteDeveloper(@PathVariable String username) {
        boolean deleted = developerService.deleteByUsername(username);
        return deleted ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Developer not found");
    }
}



