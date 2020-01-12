package org.gaming.tavern.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
public class BoardGameRatingPk implements Serializable {
    @ManyToOne
    private BoardGame boardGame;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer reviewerId;

    public BoardGameRatingPk() {
    }

    public BoardGameRatingPk(BoardGame boardGame, Integer reviewerId) {
        this.boardGame = boardGame;
        this.reviewerId = reviewerId;
    }

    public BoardGame getBoardGame() {
        return boardGame;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BoardGameRatingPk that = (BoardGameRatingPk) o;

        if (!boardGame.equals(that.boardGame)) return false;
        return reviewerId.equals(that.reviewerId);

    }

    @Override
    public int hashCode() {
        int result = boardGame.hashCode();
        result = 31 * result + reviewerId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BoardGameRatingPk{" +
                "boardGame=" + boardGame +
                ", reviewerId=" + reviewerId +
                '}';
    }
}
