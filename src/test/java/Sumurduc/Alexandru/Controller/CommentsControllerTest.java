package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Comment;
import Sumurduc.Alexandru.Services.CommentsService;
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

@WebMvcTest(CommentsController.class)
class CommentsControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private CommentsService commentsService;
    @Test
    void getAverageVote_returns200() throws Exception {
        when(commentsService.getAverageVote(2)).thenReturn(0.5);

        mockMvc.perform(get("/comments/game/2/avg"))
                .andExpect(status().isOk());

        verify(commentsService, times(1)).getAverageVote(2);
    }

    @Test
    void addOrUpdate_returns201() throws Exception {
        Comment c = new Comment();
        c.setPlayerId(1);
        c.setGameId(2);
        c.setVote(1);
        c.setComment("nice");

        mockMvc.perform(post("/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isCreated());

        verify(commentsService, times(1)).addOrUpdate(any(Comment.class));
    }

    @Test
    void getGameComments_returns200() throws Exception {
        when(commentsService.getGameComments(2)).thenReturn(List.of(new Comment()));

        mockMvc.perform(get("/comments/game/2"))
                .andExpect(status().isOk());

        verify(commentsService, times(1)).getGameComments(2);
    }

    @Test
    void delete_returns204_whenOk() throws Exception {
        when(commentsService.delete(1, 2)).thenReturn(true);

        mockMvc.perform(delete("/comments/1/2"))
                .andExpect(status().isNoContent());

        verify(commentsService, times(1)).delete(1, 2);
    }
}
