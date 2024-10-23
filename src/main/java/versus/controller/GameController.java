package versus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.model.Game;
import versus.service.GameServiceImpl;
import versus.service.interfaces.GameService;

import java.util.List;

public class GameController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public Game getGameById(long id) {
        LOGGER.info("Getting game by id: " + id);
        return gameService.getGameById(id);
    }

    public Game createGame(Game game) {
        LOGGER.info("Creating new game: " + game.getName());
        return gameService.createGame(game);
    }

    public Game updateGame(Game game) {
        LOGGER.info("Updating game: " + game.getName());
        return gameService.updateGame(game);
    }

    public void deleteGame(long id) {
        LOGGER.info("Deleting game with id: " + id);
        gameService.deleteGame(id);
    }

    public List<Game> getAllGames() {
        LOGGER.info("Getting all games");
        return gameService.getAllGames();
    }



}
