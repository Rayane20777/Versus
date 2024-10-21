package versus.service;

import versus.repository.interfaces.GameRepository;
import versus.service.interfaces.GameService;

public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
}
