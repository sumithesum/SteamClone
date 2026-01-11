package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Message;
import Sumurduc.Alexandru.Repository.MessagesRepository;
import Sumurduc.Alexandru.Services.MessagesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessagesServiceTest {

    @Mock
    private MessagesRepository messagesRepository;

    @InjectMocks
    private MessagesService messagesService;

    @Test
    void inbox_returnsList() {
        when(messagesRepository.inbox(2)).thenReturn(List.of(new Message()));
        assertEquals(1, messagesService.inbox(2).size());
        verify(messagesRepository, times(1)).inbox(2);
    }

    @Test
    void markAsRead_returnsTrueWhenUpdated() {
        when(messagesRepository.markAsRead(10)).thenReturn(1);
        assertTrue(messagesService.markAsRead(10));
    }

    @Test
    void send_throwsWhenMessagingSelf() {
        Message m = new Message();
        m.setSenderId(1);
        m.setReceiverId(1);
        m.setMessage("hi");

        assertThrows(IllegalArgumentException.class, () -> messagesService.send(m));
        verify(messagesRepository, never()).send(any());
    }
}
