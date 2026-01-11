package Sumurduc.Alexandru.Model;

public class Friends {
    private Integer playerId;
    private Integer friendId;

    public Friends() {}

    public Friends(Integer playerId, Integer friendId) {
        this.playerId = playerId;
        this.friendId = friendId;
    }

    public Integer getPlayerId() { return playerId; }
    public void setPlayerId(Integer playerId) { this.playerId = playerId; }

    public Integer getFriendId() { return friendId; }
    public void setFriendId(Integer friendId) { this.friendId = friendId; }
}
