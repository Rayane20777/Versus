package versus.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.model.Player;
import versus.service.interfaces.PlayerService;

import java.util.List;

public class PlayerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Player getPlayerById(long id) {
        LOGGER.info("Getting player by id: " + id);
        return playerService.getPlayerById(id);
    }

    public Player createPlayer(Player player) {
        LOGGER.info("Creating new player: " + player.getName());
        return playerService.createPlayer(player);
    }

    public Player updatePlayer(Player player) {
        LOGGER.info("Updating player: " + player.getName());
        return playerService.updatePlayer(player);
    }

    public void deletePlayer(long id) {
        LOGGER.info("Deleting player with id: " + id);
        playerService.deletePlayer(id);
    }

    public List<Player> getAllPlayers() {
        LOGGER.info("Getting all players");
        return playerService.getAllPlayers();
    }
}
