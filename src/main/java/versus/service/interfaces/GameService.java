package versus.service.interfaces;

import versus.model.Game;

import java.util.List;

public interface GameService {
    Game createGame(Game game);
    Game updateGame(Game game);
    Game getGameById(int id);
    List<Game> getAllGames();
    void deleteGame(int id);

}
