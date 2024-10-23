package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Player;
import versus.repository.interfaces.PlayerRepository;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class PlayerRepositoryImpl implements PlayerRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);




    @Override
    public Player createPlayer(Player player) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Saving player: {}", player.getName());
            entityManager.persist(player);
            transaction.commit();
            LOGGER.info("Player saved successfully with info: {}", player);
            return player;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error saving player: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Player updatePlayer(Player player) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Updating player: {}", player.getName());
            entityManager.merge(player);
            transaction.commit();
            LOGGER.info("Player updated successfully with info: {}", player);
            return player;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error updating player: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Player> getAllPlayers() {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();

        try {
            LOGGER.info("Finding all players");
            return entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding all players: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Player getPlayerById(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();

        try {
            LOGGER.info("Finding player with id: {}", id);
            return entityManager.find(Player.class, id);
        } catch (Exception e) {
            LOGGER.error("Error finding player with id: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deletePlayer(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Deleting player with id: {}", id);
            Player player = entityManager.find(Player.class, id);
            if (player != null) {
                entityManager.remove(player);
            }
            transaction.commit();
            LOGGER.info("Player deleted successfully");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting player: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
