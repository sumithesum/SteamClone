package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Library;
import Sumurduc.Alexandru.Model.LibraryPurchaseRequest;
import Sumurduc.Alexandru.Services.LibraryService;
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

@WebMvcTest(LibraryController.class)
class LibraryControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private LibraryService libraryService;

    @Test
    void purchase_returns201() throws Exception {
        LibraryPurchaseRequest req = new LibraryPurchaseRequest();
        req.setPlayerId(1);
        req.setGameId(2);

        mockMvc.perform(post("/library/purchase")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated());

        verify(libraryService, times(1)).purchase(1, 2);
    }

    @Test
    void getLibrary_returns200() throws Exception {
        when(libraryService.getLibrary(1)).thenReturn(List.of(new Library()));

        mockMvc.perform(get("/library/1"))
                .andExpect(status().isOk());

        verify(libraryService, times(1)).getLibrary(1);
    }

    @Test
    void updateHours_returns204_whenOk() throws Exception {
        when(libraryService.updateHours(1, 2, 5f)).thenReturn(true);

        mockMvc.perform(patch("/library/1/2/hours")
                        .param("hoursPlayed", "5"))
                .andExpect(status().isNoContent());

        verify(libraryService, times(1)).updateHours(1, 2, 5f);
    }
}
