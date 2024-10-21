package versus.repository.interfaces;

import versus.model.Player;

import java.util.List;

public interface PlayerRepository {
    Player getPlayerById(int id);
    List<Player> getAllPlayers();
    Player createPlayer(Player player);
    Player updatePlayer(Player player);
    void deletePlayer(int id);
}
