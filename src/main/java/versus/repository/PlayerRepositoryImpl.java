package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Player;
import versus.repository.interfaces.PlayerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class PlayerRepositoryImpl implements PlayerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Player createPlayer(Player player) {
        LOGGER.info("Creating new player: {}", player.getName());
        entityManager.getTransaction().begin();
        entityManager.persist(player);
        entityManager.getTransaction().commit();
        LOGGER.info("Player created successfully with info: {}", player);
        return player;
    }

    @Override
    public Player updatePlayer(Player player) {
        LOGGER.info("Updating player: {}", player.getName());
        entityManager.getTransaction().begin();
        entityManager.merge(player);
        entityManager.getTransaction().commit();
        LOGGER.info("Player updated successfully with info: {}", player);
        return player;
    }

    @Override
    public List<Player> getAllPlayers() {
        LOGGER.info("Getting all players");
        return entityManager.createQuery("from Player", Player.class).getResultList();
    }

    @Override
    public Player getPlayerById(long id) {
        LOGGER.info("Getting player by id: {}", id);
        return entityManager.find(Player.class, id);
    }

    @Override
    public void deletePlayer(long id) {
        LOGGER.info("Player found successfully with info: {}", id);
        Player player = getPlayerById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(player);
        entityManager.getTransaction().commit();
        LOGGER.info("Player found successfully with info: {}", id);
    }
}
