package Sumurduc.Alexandru.Model;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class Library {

    @NotNull(message = "playerId is required")
    private Integer playerId;

    @NotNull(message = "gameId is required")
    private Integer gameId;

    @PositiveOrZero(message = "hoursPlayed must be >= 0")
    private Float hoursPlayed;

    private LocalDateTime acquiredAt;

    public Library() {}

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }

    public Float getHoursPlayed() { return hoursPlayed; }
    public void setHoursPlayed(Float hoursPlayed) { this.hoursPlayed = hoursPlayed; }

    public LocalDateTime getAcquiredAt() { return acquiredAt; }
    public void setAcquiredAt(LocalDateTime acquiredAt) { this.acquiredAt = acquiredAt; }
}
