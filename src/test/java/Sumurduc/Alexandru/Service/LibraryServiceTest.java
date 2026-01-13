package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Repository.*;
import Sumurduc.Alexandru.Services.LibraryService;
import Sumurduc.Alexandru.Model.Library;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LibraryServiceTest {

    @Mock private LibraryRepository libraryRepository;
    @Mock private GameRepository gameRepository;
    @Mock private PlayerRepository playerRepository;
    @Mock private DeveloperRepository developerRepository;

    @InjectMocks private LibraryService libraryService;

    @Test
    void getLibrary_returnsList() {
        when(libraryRepository.findByPlayerId(1)).thenReturn(List.of(new Library()));
        assertEquals(1, libraryService.getLibrary(1).size());
        verify(libraryRepository, times(1)).findByPlayerId(1);
    }

    @Test
    void updateHours_returnsTrueWhenUpdated() {
        when(libraryRepository.updateHours(1, 2, 3.5f)).thenReturn(1);
        assertTrue(libraryService.updateHours(1, 2, 3.5f));
    }

    @Test
    void updateHours_throwsWhenNegative() {
        assertThrows(IllegalArgumentException.class, () -> libraryService.updateHours(1, 2, -1f));
    }

    @Test
    void purchase_transfersMoneyAndAddsToLibrary() {
        // game price 100, discount 10% => final 90
        when(gameRepository.findPurchaseInfoById(2))
                .thenReturn(new GameRepository.GamePurchaseInfo(10, 100f, 10f));

        when(playerRepository.subtractMoneyIfEnough(1, 90f)).thenReturn(1);
        when(developerRepository.addMoney(10, 90f)).thenReturn(1);
        when(libraryRepository.purchase(1, 2)).thenReturn(1);

        libraryService.purchase(1, 2);

        verify(playerRepository).subtractMoneyIfEnough(1, 90f);
        verify(developerRepository).addMoney(10, 90f);
        verify(libraryRepository).purchase(1, 2);
    }

    @Test
    void purchase_throwsWhenInsufficientFunds() {
        when(gameRepository.findPurchaseInfoById(2))
                .thenReturn(new GameRepository.GamePurchaseInfo(10, 100f, 0f));

        when(playerRepository.subtractMoneyIfEnough(1, 100f)).thenReturn(0);

        assertThrows(IllegalArgumentException.class, () -> libraryService.purchase(1, 2));

        verify(developerRepository, never()).addMoney(anyInt(), anyFloat());
        verify(libraryRepository, never()).purchase(anyInt(), anyInt());
    }
}
