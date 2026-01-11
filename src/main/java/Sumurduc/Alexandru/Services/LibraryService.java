package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Library;
import Sumurduc.Alexandru.Repository.LibraryRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public void purchase(Integer playerId, Integer gameId) {
        try {
            libraryRepository.purchase(playerId, gameId);
        } catch (DuplicateKeyException ex) {
            throw new IllegalArgumentException("Game already in library.");
        }
    }

    public List<Library> getLibrary(Integer playerId) {
        return libraryRepository.findByPlayerId(playerId);
    }

    public boolean updateHours(Integer playerId, Integer gameId, Float hoursPlayed) {
        if (hoursPlayed == null || hoursPlayed < 0) {
            throw new IllegalArgumentException("hoursPlayed must be >= 0");
        }
        return libraryRepository.updateHours(playerId, gameId, hoursPlayed) > 0;
    }
}
