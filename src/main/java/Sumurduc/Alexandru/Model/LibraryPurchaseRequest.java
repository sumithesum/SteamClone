package Sumurduc.Alexandru.Model;

import jakarta.validation.constraints.NotNull;

public class LibraryPurchaseRequest {

    @NotNull(message = "playerId is required")
    private Integer playerId;

    @NotNull(message = "gameId is required")
    private Integer gameId;

    public LibraryPurchaseRequest() {}

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }
}
