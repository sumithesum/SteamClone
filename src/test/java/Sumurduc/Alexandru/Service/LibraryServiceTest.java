package Sumurduc.Alexandru.Service;

import Sumurduc.Alexandru.Model.Library;
import Sumurduc.Alexandru.Repository.LibraryRepository;
import Sumurduc.Alexandru.Services.LibraryService;
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

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

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
}
