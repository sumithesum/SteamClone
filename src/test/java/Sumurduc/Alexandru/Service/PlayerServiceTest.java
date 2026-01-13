package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Repository.PlayerRepository;
import Sumurduc.Alexandru.Services.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    @Test
    void getAllPlayers_returnsList() {
        when(playerRepository.findAll()).thenReturn(List.of(new Player()));
        assertEquals(1, playerService.getAllPlayers().size());
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    void deleteByUsername_returnsTrueWhenDeleted() {
        when(playerRepository.deleteByUsername("alex")).thenReturn(1);
        assertTrue(playerService.deleteByUsername("alex"));
    }

    @Test
    void deleteByUsername_returnsFalseWhenNotFound() {
        when(playerRepository.deleteByUsername("missing")).thenReturn(0);
        assertFalse(playerService.deleteByUsername("missing"));
    }
    @Test
    void addMoney_returnsTrueWhenUpdated() {
        when(playerRepository.addMoney(1, 50f)).thenReturn(1);
        assertTrue(playerService.addMoney(1, 50f));
    }

    @Test
    void addMoney_throwsWhenNegative() {
        assertThrows(IllegalArgumentException.class, () -> playerService.addMoney(1, -10f));
    }

}
