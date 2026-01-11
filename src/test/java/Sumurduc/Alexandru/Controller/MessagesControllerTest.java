package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Message;
import Sumurduc.Alexandru.Services.MessagesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MessagesController.class)
class MessagesControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private MessagesService messagesService;

    @Test
    void send_returns201() throws Exception {
        Message m = new Message();
        m.setSenderId(1);
        m.setReceiverId(2);
        m.setMessage("hello");

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(m)))
                .andExpect(status().isCreated());

        verify(messagesService, times(1)).send(any(Message.class));
    }

    @Test
    void inbox_returns200() throws Exception {
        when(messagesService.inbox(2)).thenReturn(List.of(new Message()));

        mockMvc.perform(get("/messages/inbox/2"))
                .andExpect(status().isOk());

        verify(messagesService, times(1)).inbox(2);
    }

    @Test
    void markRead_returns204_whenOk() throws Exception {
        when(messagesService.markAsRead(10)).thenReturn(true);

        mockMvc.perform(patch("/messages/10/read"))
                .andExpect(status().isNoContent());

        verify(messagesService, times(1)).markAsRead(10);
    }
}
