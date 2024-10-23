package versus.service;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Game;
import versus.repository.interfaces.GameRepository;
import versus.service.interfaces.GameService;

import java.util.List;

@Transactional
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(Game
            game) {
        return gameRepository.save(game);
    }

    @Override
    public Game updateGame(Game game) {
        return gameRepository.update(game);
    }

    @Override
    public Game getGameById(long id) {
        return gameRepository.findById(id);
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public void deleteGame(long id) {
        gameRepository.delete(id);
    }
}
