package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Game;
import Sumurduc.Alexandru.Services.GameService;
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

@WebMvcTest(GameController.class)
class GameControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private GameService gameService;

    @Test
    void getAllGames_returns200() throws Exception {
        when(gameService.getAllGames()).thenReturn(List.of(new Game()));
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk());
        verify(gameService, times(1)).getAllGames();
    }

    @Test
    void addGame_returns201() throws Exception {
        Game game = new Game();
        game.setDevId(1);
        game.setTitle("My Game");
        game.setTags("tag1,tag2");
        game.setDescription("desc");
        game.setPrice(10f);
        game.setDiscount(0);

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(game)))
                .andExpect(status().isCreated());

        verify(gameService, times(1)).addGame(any(Game.class));
    }

    @Test
    void deleteGame_returns204_whenDeleted() throws Exception {
        when(gameService.deleteByTitle("abc")).thenReturn(true);

        mockMvc.perform(delete("/games/abc"))
                .andExpect(status().isNoContent());

        verify(gameService, times(1)).deleteByTitle("abc");
    }
}
