package Sumurduc.Alexandru.Model;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class Comment {

    @NotNull(message = "playerId is required")
    private Integer playerId;

    @NotNull(message = "gameId is required")
    private Integer gameId;

    @Size(max = 5000, message = "comment too long")
    private String comment;


    @NotNull(message = "vote is required")
    @Min(value = -1, message = "vote must be >= -1")
    @Max(value = 1, message = "vote must be <= 1")
    private Integer vote;

    private LocalDateTime createdAt;

    public Comment() {}

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Integer getVote() { return vote; }
    public void setVote(Integer vote) { this.vote = vote; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
