package org.gaming.tavern.repo;

import org.gaming.tavern.domain.BoardGameCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface BoardGameCategoryRepository extends CrudRepository<BoardGameCategory, String> {

    Optional<BoardGameCategory> findByName(@Param("name")String name);

    @Override
    @RestResource(exported = false)
    <S extends BoardGameCategory> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends BoardGameCategory> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String s);

    @Override
    @RestResource(exported = false)
    void delete(BoardGameCategory boardGameCategory);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends BoardGameCategory> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
}

