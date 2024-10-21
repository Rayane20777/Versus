package versus.repository.interfaces;

import versus.model.Game;

import java.util.List;

public interface GameRepository {
    Game findById(int id);
    List<Game> findAll();
    Game save(Game game);
    Game update(Game game);
    void delete(int id);
}
