package org.gaming.tavern.repo;

import org.gaming.tavern.domain.BoardGame;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface BoardGameRepository extends PagingAndSortingRepository<BoardGame, Integer> {

    Page<BoardGame> findByBoardGameCategoryCode(@Param("code")String code, Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends BoardGame> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends BoardGame> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    void delete(BoardGame boardGame);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends BoardGame> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}
