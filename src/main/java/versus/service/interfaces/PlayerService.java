package versus.service.interfaces;

import versus.model.Player;

import java.util.List;

public interface PlayerService {
    Player createPlayer(Player player);
    Player updatePlayer(Player player);
    Player getPlayerById(long id);
    List<Player> getAllPlayers();
    void deletePlayer(long id);
}
