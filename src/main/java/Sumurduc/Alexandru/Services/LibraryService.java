package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Library;
import Sumurduc.Alexandru.Repository.LibraryRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import Sumurduc.Alexandru.Repository.GameRepository;
import Sumurduc.Alexandru.Repository.PlayerRepository;
import Sumurduc.Alexandru.Repository.DeveloperRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;
    private final DeveloperRepository developerRepository;

    public LibraryService(LibraryRepository libraryRepository, GameRepository gameRepository, PlayerRepository playerRepository, DeveloperRepository developerRepository) {
        this.libraryRepository = libraryRepository;
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
        this.developerRepository = developerRepository;
    }

//    public void purchase(Integer playerId, Integer gameId) {
//        try {
//            libraryRepository.purchase(playerId, gameId);
//        } catch (DuplicateKeyException ex) {
//            throw new IllegalArgumentException("Game already in library.");
//        }
//    }

    public List<Library> getLibrary(Integer playerId) {
        return libraryRepository.findByPlayerId(playerId);
    }

    public boolean updateHours(Integer playerId, Integer gameId, Float hoursPlayed) {
        if (hoursPlayed == null || hoursPlayed < 0) {
            throw new IllegalArgumentException("hoursPlayed must be >= 0");
        }
        return libraryRepository.updateHours(playerId, gameId, hoursPlayed) > 0;
    }

    @Transactional
    public void purchase(Integer playerId, Integer gameId) {
        var info = gameRepository.findPurchaseInfoById(gameId);
        if (info == null) {
            throw new IllegalArgumentException("Game does not exist.");
        }

        float price = info.price();
        float discountPercent = info.discount() == null ? 0f : info.discount();
        float finalPrice = price * (1f - (discountPercent / 100f));

        if (finalPrice < 0) finalPrice = 0;

        // 1) scade bani doar dacă are suficienți
        int updated = playerRepository.subtractMoneyIfEnough(playerId, finalPrice);
        if (updated == 0) {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        // 2) adaugă bani la developer
        int devUpdated = developerRepository.addMoney(info.devId(), finalPrice);
        if (devUpdated == 0) {
            // developer inexistent => rollback
            throw new IllegalArgumentException("Developer not found for game.");
        }

        // 3) adaugă jocul în library
        try {
            libraryRepository.purchase(playerId, gameId);
        } catch (org.springframework.dao.DuplicateKeyException ex) {
            // dacă îl are deja, aruncăm ca să facă rollback și să nu rămână banii mutați
            throw new IllegalArgumentException("Game already in library.");
        }
        }

}
