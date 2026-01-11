package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Library;
import Sumurduc.Alexandru.Model.LibraryPurchaseRequest;
import Sumurduc.Alexandru.Services.LibraryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@Valid @RequestBody LibraryPurchaseRequest req) {
        libraryService.purchase(req.getPlayerId(), req.getGameId());
        return ResponseEntity.status(HttpStatus.CREATED).body("Game purchased");
    }

    @GetMapping("/{playerId}")
    public List<Library> getLibrary(@PathVariable Integer playerId) {
        return libraryService.getLibrary(playerId);
    }

    @PatchMapping("/{playerId}/{gameId}/hours")
    public ResponseEntity<?> updateHours(
            @PathVariable Integer playerId,
            @PathVariable Integer gameId,
            @RequestParam Float hoursPlayed
    ) {
        boolean ok = libraryService.updateHours(playerId, gameId, hoursPlayed);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Library entry not found");
    }
}
