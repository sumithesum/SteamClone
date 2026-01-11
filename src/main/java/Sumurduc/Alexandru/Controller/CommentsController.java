package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Comment;
import Sumurduc.Alexandru.Services.CommentsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }


    @GetMapping("/game/{gameId}/avg")
    public double getAverageVote(@PathVariable Integer gameId) {
        return commentsService.getAverageVote(gameId);
    }

    @PostMapping
    public ResponseEntity<String> addOrUpdate(@Valid @RequestBody Comment comment) {
        commentsService.addOrUpdate(comment);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment saved");
    }

    @GetMapping("/game/{gameId}")
    public List<Comment> getGameComments(@PathVariable Integer gameId) {
        return commentsService.getGameComments(gameId);
    }

    @DeleteMapping("/{playerId}/{gameId}")
    public ResponseEntity<?> delete(@PathVariable Integer playerId, @PathVariable Integer gameId) {
        boolean ok = commentsService.delete(playerId, gameId);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment not found");
    }
}
