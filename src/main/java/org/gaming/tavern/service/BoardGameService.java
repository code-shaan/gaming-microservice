package com.example.ec.service;

import org.example.ec.domain.BestPlayedAt;
import org.example.ec.domain.BoardGame;
import org.example.ec.domain.BoardGameCategory;
import org.example.ec.domain.Difficulty;
import org.example.ec.repo.BoardGameCategoryRepository;
import org.example.ec.repo.BoardGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardGameService {
    private BoardGameRepository boardGameRepository;
    private BoardGameCategoryRepository boardGameCategoryRepository;

    @Autowired
    public BoardGameService(BoardGameRepository boardGameRepository, BoardGameCategoryRepository boardGameCategoryRepository) {
        this.boardGameRepository = boardGameRepository;
        this.boardGameCategoryRepository = boardGameCategoryRepository;
    }

    public BoardGame createBoardGame(String title, String description, Integer price,
                           String playtime, String keywords, String boardGameCategoryName, Difficulty difficulty, BestPlayedAt bestPlayedAt ) {
        BoardGameCategory boardGameCategory = boardGameCategoryRepository.findByName(boardGameCategoryName).orElseThrow(()->
             new RuntimeException("BoardGame category does not exist: " + boardGameCategoryName));

        return boardGameRepository.save(new BoardGame(title, description, price, playtime,
                keywords, boardGameCategory, difficulty, bestPlayedAt));
    }

    public long total() {
        return boardGameRepository.count();
    }
}

