package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Message;
import Sumurduc.Alexandru.Services.MessagesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessagesController {

    private final MessagesService messagesService;

    public MessagesController(MessagesService messagesService) {
        this.messagesService = messagesService;
    }

    @PostMapping
    public ResponseEntity<String> send(@Valid @RequestBody Message msg) {
        messagesService.send(msg);
        return ResponseEntity.status(HttpStatus.CREATED).body("Message sent");
    }

    @GetMapping("/inbox/{playerId}")
    public List<Message> inbox(@PathVariable Integer playerId) {
        return messagesService.inbox(playerId);
    }

    @PatchMapping("/{messageId}/read")
    public ResponseEntity<?> markRead(@PathVariable Integer messageId) {
        boolean ok = messagesService.markAsRead(messageId);
        return ok ? ResponseEntity.noContent().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found");
    }
}
