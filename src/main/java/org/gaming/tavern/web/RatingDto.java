package com.example.ec.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.ec.domain.BoardGameRating;

public class RatingDto {

    @Min(1)
    @Max(10)
    private Integer score;

    @Size(max = 500)
    private String comment;

    @NotNull
    private Integer reviewerId;

    public RatingDto(BoardGameRating boardGameRating) {
        this(boardGameRating.getScore(), boardGameRating.getComment(), boardGameRating.getPk().getReviewerId());
    }
    
    private RatingDto(Integer score, String comment, Integer reviewerId) {
        this.score = score;
        this.comment = comment;
        this.reviewerId = reviewerId;
    }

    protected RatingDto() {}

    public Integer getScore() {
        return score;
    }

    public String getComment() {
        return comment;
    }

    public Integer getReviewerId() {
        return reviewerId;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setReviewerId(Integer reviewerId) {
        this.reviewerId = reviewerId;
    }

}
