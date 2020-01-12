package org.gaming.tavern.web;

import org.gaming.tavern.domain.BoardGame;
import org.gaming.tavern.domain.BoardGameRating;
import org.gaming.tavern.domain.BoardGameRatingPk;
import org.gaming.tavern.repo.BoardGameRatingRepository;
import org.gaming.tavern.repo.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/games/{gameId}/ratings")
public class BoardGameRatingController {
    private BoardGameRatingRepository boardGameRatingRepository;
    private BoardGameRepository boardGameRepository;

    @Autowired
    public BoardGameRatingController(BoardGameRatingRepository boardGameRatingRepository, BoardGameRepository boardGameRepository) {
        this.boardGameRatingRepository = boardGameRatingRepository;
        this.boardGameRepository = boardGameRepository;
    }

    protected BoardGameRatingController() {

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBoardGameRating(@PathVariable(value = "gameId") int gameId, @RequestBody @Validated RatingDto ratingDto) {
        BoardGame boardGame = verifyBoardGame(gameId);
        boardGameRatingRepository.save(new BoardGameRating( new BoardGameRatingPk(boardGame, ratingDto.getReviewerId()),
                ratingDto.getScore(), ratingDto.getComment()));
    }

    @GetMapping
    public Page<RatingDto> getRatings(@PathVariable(value = "gameId") int gameId, Pageable pageable){
        verifyBoardGame(gameId);
        Page<BoardGameRating> ratings = boardGameRatingRepository.findByPkBoardGameId(gameId, pageable);
        return new PageImpl<>(
                ratings.get().map(RatingDto::new).collect(Collectors.toList()),
                pageable,
                ratings.getTotalElements()
        );
    }

    @PutMapping
    public RatingDto updateWithPut(@PathVariable(value = "gameId") int gameId, @RequestBody @Validated RatingDto ratingDto) {
        BoardGameRating rating = verifyBoardGameRating(gameId, ratingDto.getReviewerId());
        rating.setScore(ratingDto.getScore());
        rating.setComment(ratingDto.getComment());
        return new RatingDto(boardGameRatingRepository.save(rating));
    }
   
    @PatchMapping
    public RatingDto updateWithPatch(@PathVariable(value = "gameId") int gameId, @RequestBody @Validated RatingDto ratingDto) {
        BoardGameRating rating = verifyBoardGameRating(gameId, ratingDto.getReviewerId());
        if (ratingDto.getScore() != null) {
            rating.setScore(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null) {
            rating.setComment(ratingDto.getComment());
        }
        return new RatingDto(boardGameRatingRepository.save(rating));
    }

    @DeleteMapping(path = "/{reviwerId}")
    public void delete(@PathVariable(value = "gameId") int gameId, @PathVariable(value = "reviewerId") int reviewerId) {
        BoardGameRating rating = verifyBoardGameRating(gameId, reviewerId);
        boardGameRatingRepository.delete(rating);
    }

    private BoardGameRating verifyBoardGameRating(int gameId, int reviewerId) throws NoSuchElementException {
        return boardGameRatingRepository.findByPkBoardGameIdAndPkReviewerId(gameId, reviewerId).orElseThrow(() ->
                new NoSuchElementException("BoardGame-Rating pair for request("
                + gameId + " for reviewer" + reviewerId));
    }

    private BoardGame verifyBoardGame(int gameId) throws NoSuchElementException {
        return boardGameRepository.findById(gameId).orElseThrow(() ->
            new NoSuchElementException("BoardGame does not exist " + gameId));
        }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();

    }

}
