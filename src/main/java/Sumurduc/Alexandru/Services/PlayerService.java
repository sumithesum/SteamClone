package Sumurduc.Alexandru.Services;

import Sumurduc.Alexandru.Model.Player;
import Sumurduc.Alexandru.Repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public void addPlayer(Player player) {
        playerRepository.add(player);
    }

    public boolean deleteByUsername(String username) {
        return playerRepository.deleteByUsername(username) > 0;
    }
}
