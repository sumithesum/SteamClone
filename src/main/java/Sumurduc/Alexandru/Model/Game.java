package Sumurduc.Alexandru.Model;

import jakarta.validation.constraints.*;

public class Game {
    private Integer gameId;

    @NotNull(message = "devId is required")
    private Integer devId;

    @NotBlank(message = "title is required")
    @Size(max = 255, message = "title too long")
    private String title;

    @Size(max = 255, message = "tags too long")
    private String tags;

    @Size(max = 5000, message = "description too long")
    private String description;

    @NotNull(message = "price is required")
    @PositiveOrZero(message = "price must be >= 0")
    private Float price;

    @NotNull(message = "discount is required")
    @Min(value = 0, message = "discount must be >= 0")
    @Max(value = 100, message = "discount must be <= 100")
    private Integer discount;

    public Game() {}

    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }

    public Integer getDevId() { return devId; }
    public void setDevId(Integer devId) { this.devId = devId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Float getPrice() { return price; }
    public void setPrice(Float price) { this.price = price; }

    public Integer getDiscount() { return discount; }
    public void setDiscount(Integer discount) { this.discount = discount; }
}
