package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Message;
import Sumurduc.Alexandru.Repository.MessagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesService {

    private final MessagesRepository messagesRepository;

    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void send(Message msg) {
        if (msg.getSenderId().equals(msg.getReceiverId())) {
            throw new IllegalArgumentException("You cannot message yourself.");
        }
        messagesRepository.send(msg);
    }

    public List<Message> inbox(Integer receiverId) {
        return messagesRepository.inbox(receiverId);
    }

    public boolean markAsRead(Integer messageId) {
        return messagesRepository.markAsRead(messageId) > 0;
    }
}
