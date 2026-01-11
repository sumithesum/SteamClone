package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Game;
import Sumurduc.Alexandru.Repository.GameRepository;
import Sumurduc.Alexandru.Services.GameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    @Test
    void getAllGames_returnsList() {
        when(gameRepository.findAll()).thenReturn(List.of(new Game()));
        List<Game> result = gameService.getAllGames();
        assertEquals(1, result.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    void deleteByTitle_returnsTrueWhenRowDeleted() {
        when(gameRepository.delete("abc")).thenReturn(1);
        assertTrue(gameService.deleteByTitle("abc"));
    }

    @Test
    void deleteByTitle_returnsFalseWhenNothingDeleted() {
        when(gameRepository.delete("missing")).thenReturn(0);
        assertFalse(gameService.deleteByTitle("missing"));
    }
}