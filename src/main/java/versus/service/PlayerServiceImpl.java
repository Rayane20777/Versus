package versus.service;

import versus.repository.PlayerRepositoryImpl;
import versus.repository.interfaces.PlayerRepository;


public class PlayerServiceImpl impelements PlayerService {
    private PlayerRepository playerRepository;
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
}
