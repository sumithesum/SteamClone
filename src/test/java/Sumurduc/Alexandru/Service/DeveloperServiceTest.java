package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Developer;
import Sumurduc.Alexandru.Repository.DeveloperRepository;
import Sumurduc.Alexandru.Services.DeveloperService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeveloperServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @InjectMocks
    private DeveloperService developerService;

    @Test
    void getAllDevelopers_returnsList() {
        when(developerRepository.findAll()).thenReturn(List.of(new Developer()));
        assertEquals(1, developerService.getAllDevelopers().size());
    }

    @Test
    void deleteByUsername_returnsTrue() {
        when(developerRepository.deleteByUsername("dev")).thenReturn(1);
        assertTrue(developerService.deleteByUsername("dev"));
    }

    @Test
    void deleteByUsername_returnsFalse() {
        when(developerRepository.deleteByUsername("missing")).thenReturn(0);
        assertFalse(developerService.deleteByUsername("missing"));
    }
}
