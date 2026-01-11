package Sumurduc.Alexandru.Controller;

import Sumurduc.Alexandru.Model.Developer;
import Sumurduc.Alexandru.Services.DeveloperService;
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

@WebMvcTest(DeveloperController.class)
class DeveloperControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @MockBean private DeveloperService developerService;

    @Test
    void getDevelopers_returns200() throws Exception {
        when(developerService.getAllDevelopers()).thenReturn(List.of(new Developer()));

        mockMvc.perform(get("/developers"))
                .andExpect(status().isOk());
    }

    @Test
    void addDeveloper_returns201() throws Exception {
        Developer d = new Developer();
        d.setUsername("dev");
        d.setPassword("password123");

        mockMvc.perform(post("/developers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(d)))
                .andExpect(status().isCreated());

        verify(developerService, times(1)).addDeveloper(any(Developer.class));
    }

    @Test
    void deleteDeveloper_returns204() throws Exception {
        when(developerService.deleteByUsername("dev")).thenReturn(true);

        mockMvc.perform(delete("/developers/dev"))
                .andExpect(status().isNoContent());
    }
}
