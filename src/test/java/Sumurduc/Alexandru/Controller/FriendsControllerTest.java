package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Services.FriendsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FriendsController.class)
class FriendsControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean private FriendsService friendsService;

    @Test
    void addFriend_returns201() throws Exception {
        mockMvc.perform(post("/friends/1/2"))
                .andExpect(status().isCreated());

        verify(friendsService, times(1)).addFriend(1, 2);
    }

    @Test
    void getFriends_returns200() throws Exception {
        when(friendsService.getFriends(1)).thenReturn(List.of(new Player()));

        mockMvc.perform(get("/friends/1"))
                .andExpect(status().isOk());

        verify(friendsService, times(1)).getFriends(1);
    }

    @Test
    void deleteFriend_returns204_whenDeleted() throws Exception {
        when(friendsService.deleteFriendship(1, 2)).thenReturn(true);

        mockMvc.perform(delete("/friends/1/2"))
                .andExpect(status().isNoContent());

        verify(friendsService, times(1)).deleteFriendship(1, 2);
    }
}
