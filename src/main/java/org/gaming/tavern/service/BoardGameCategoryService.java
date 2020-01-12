package org.gaming.tavern.service;

import org.gaming.tavern.domain.BoardGameCategory;
import org.gaming.tavern.repo.BoardGameCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardGameCategoryService {
    private BoardGameCategoryRepository boardGameCategoryRepository;

    @Autowired
    public BoardGameCategoryService(BoardGameCategoryRepository boardGameCategoryRepository) {
        this.boardGameCategoryRepository = boardGameCategoryRepository;
    }

    public BoardGameCategory createBoardGameCategory(String code, String name) {
        return boardGameCategoryRepository.findById(code).orElse(boardGameCategoryRepository.save(new BoardGameCategory(code, name)));
    }

    public Iterable<BoardGameCategory> lookup(){
        return boardGameCategoryRepository.findAll();
    }

    public long total() {
        return boardGameCategoryRepository.count();
    }
}

