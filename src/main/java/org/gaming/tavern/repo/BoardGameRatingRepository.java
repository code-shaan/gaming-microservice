package org.gaming.tavern.repo;

import org.gaming.tavern.domain.BoardGameRating;
import org.gaming.tavern.domain.BoardGameRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface BoardGameRatingRepository extends CrudRepository<BoardGameRating, BoardGameRatingPk> {

    List<BoardGameRating> findByPkBoardGameId(Integer gameId);

    Optional<BoardGameRating> findByPkBoardGameIdAndPkReviewerId(Integer gameId, Integer reviewerId);

    Page<BoardGameRating> findByPkBoardGameId(Integer gameId, Pageable pageable);
}