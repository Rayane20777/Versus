package versus.service;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Player;
import versus.repository.interfaces.PlayerRepository;
import versus.service.interfaces.PlayerService;

import java.util.List;


@Transactional
public class PlayerServiceImpl implements PlayerService {
    private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Player createPlayer(Player
                                   Player) {
        return playerRepository.createPlayer(Player);
    }

    @Override
    public Player updatePlayer(Player Player) {
        return playerRepository.updatePlayer(Player);
    }

    @Override
    public Player getPlayerById(long id) {
        return playerRepository.getPlayerById(id);
    }

    @Override
    public List<Player> getAllPlayers() {
        return playerRepository.getAllPlayers();
    }

    @Override
    public void deletePlayer(long id) {
        playerRepository.deletePlayer(id);
    }
}
