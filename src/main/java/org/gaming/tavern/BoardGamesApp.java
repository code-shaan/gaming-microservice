package org.gaming.tavern;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.gaming.tavern.domain.BestPlayedAt;
import org.gaming.tavern.domain.Difficulty;
import org.gaming.tavern.service.BoardGameCategoryService;
import org.gaming.tavern.service.BoardGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class BoardGamesApp implements CommandLineRunner {

    @Value("${ec.importfile}")
    private String fileImport;

    @Autowired
    private BoardGameCategoryService boardGameCategoryService;
    @Autowired
    private BoardGameService boardGameService;

    public static void main(String[] args) {
		SpringApplication.run(BoardGamesApp.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        createBoardGameCategories();
        createBoardGames(fileImport);
    }
    
	private void createBoardGameCategories(){
        boardGameCategoryService.createBoardGameCategory("CR", "Card Game");
        boardGameCategoryService.createBoardGameCategory("DC", "Dice");
        boardGameCategoryService.createBoardGameCategory("PG", "Party Game");
        boardGameCategoryService.createBoardGameCategory("RC", "Racing");
        boardGameCategoryService.createBoardGameCategory("TB", "Territory Building");
        boardGameCategoryService.createBoardGameCategory("DD", "Deduction");
        boardGameCategoryService.createBoardGameCategory("MT", "Math");
        boardGameCategoryService.createBoardGameCategory("PZ", "Puzzle");
        boardGameCategoryService.createBoardGameCategory("SP", "Sports");
    }

    private void createBoardGames(String fileToImport) throws IOException {
        BoardGameFromFile.read(fileToImport).forEach(boardGame ->
            boardGameService.createBoardGame(boardGame.getTitle(),
                    boardGame.getDescription(),
                    boardGame.getPrice(),
                    boardGame.getPlaytime(),
                    boardGame.getKeywords(),
                    boardGame.getCategoryType(),
                    boardGame.getDifficulty(),
                    boardGame.getBestPlayedAt()));
    }

    private static class BoardGameFromFile {
        private String categoryType, title, description, price, playtime,
                keywords, difficulty, bestPlayedAt;

        static List<BoardGameFromFile> read(String fileImport) throws IOException {
            return new ObjectMapper().setVisibility(FIELD, ANY).
                    readValue(new FileInputStream(fileImport), new TypeReference<List<BoardGameFromFile>>() {});
        }
        
        @SuppressWarnings("unused")
        protected BoardGameFromFile(){}

        String getCategoryType() { return categoryType; }

        String getTitle() { return title; }

        String getDescription() { return description; }
        
        String getPlaytime() { return playtime; }

        Integer getPrice() { return Integer.parseInt(price); }

        String getKeywords() { return keywords; }

        Difficulty getDifficulty() { return Difficulty.valueOf(difficulty); }

        BestPlayedAt getBestPlayedAt() { return BestPlayedAt.findByGroupSize(bestPlayedAt); }
    }
}
