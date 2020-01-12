package org.gaming.tavern.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BoardGame {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String title;

    @Column(length = 1000)
    private String description;

    @Column
    private Integer price;

    @Column
    private String playtime;

    @Column
    private String keywords;

    @ManyToOne
    private BoardGameCategory boardGameCategory;

    @Column
    @Enumerated
    private Difficulty difficulty;

    @Column
    @Enumerated
    private BestPlayedAt bestPlayedAt;

    public BoardGame(String title, String description, Integer price, String playtime,
            String keywords, BoardGameCategory boardGameCategory, Difficulty difficulty, BestPlayedAt bestPlayedAt) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.playtime = playtime;
        this.keywords = keywords;
        this.boardGameCategory = boardGameCategory;
        this.difficulty = difficulty;
        this.bestPlayedAt = bestPlayedAt;
    }

    protected BoardGame() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getKeywords() {
        return keywords;
    }

    public BoardGameCategory getBoardGameCategory() {
        return boardGameCategory;
    }

    public void setBoardGameCategory(BoardGameCategory boardGameCategory) {
        this.boardGameCategory = boardGameCategory;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public BestPlayedAt getExpansion() {
        return bestPlayedAt;
    }

    public void setExpansion(BestPlayedAt bestPlayedAt) {
        this.bestPlayedAt = bestPlayedAt;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", playtime='" + playtime + '\'' +
                ", keywords='" + keywords + '\'' +
                ", boardGameCategory=" + boardGameCategory +
                ", difficulty=" + difficulty +
                ", bestPlayedAt=" + bestPlayedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame boardGame = (BoardGame) o;
        return Objects.equals(id, boardGame.id) &&
                Objects.equals(title, boardGame.title) && Objects.equals(description, boardGame.description) &&
                Objects.equals(price, boardGame.price) && Objects.equals(playtime, boardGame.playtime) &&
                Objects.equals(keywords, boardGame.keywords) && Objects.equals(boardGameCategory, boardGame.boardGameCategory) &&
                difficulty == boardGame.difficulty && bestPlayedAt == boardGame.bestPlayedAt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, price, playtime, keywords, boardGameCategory, difficulty, bestPlayedAt);
    }
}
