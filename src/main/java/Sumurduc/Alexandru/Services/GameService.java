package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Game;
import Sumurduc.Alexandru.Repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public void addGame(Game game) {

         if (game.getPrice() < 0) throw new IllegalArgumentException("Price must be >= 0");
        gameRepository.add(game);
    }

    public boolean deleteByTitle(String title) {
        return gameRepository.delete(title) > 0;
    }

    public List<Game> getGamesOfDeveloper(String devUsername) {
        return gameRepository.findGameOfCompany(devUsername);
    }
}
