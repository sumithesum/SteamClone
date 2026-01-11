package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Services.PlayerService;
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

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private PlayerService playerService;

    @Test
    void getPlayers_returns200() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(List.of(new Player()));

        mockMvc.perform(get("/players"))
                .andExpect(status().isOk());

        verify(playerService, times(1)).getAllPlayers();
    }

    @Test
    void addPlayer_returns201() throws Exception {
        Player p = new Player();
        p.setUsername("alex");
        p.setPassword("password123");

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isCreated());

        verify(playerService, times(1)).addPlayer(any(Player.class));
    }

    @Test
    void deletePlayer_returns204_whenDeleted() throws Exception {
        when(playerService.deleteByUsername("alex")).thenReturn(true);

        mockMvc.perform(delete("/players/alex"))
                .andExpect(status().isNoContent());

        verify(playerService, times(1)).deleteByUsername("alex");
    }
}
