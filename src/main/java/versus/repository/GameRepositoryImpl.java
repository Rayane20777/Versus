package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.model.Game;
import versus.repository.interfaces.GameRepository;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GameRepositoryImpl implements GameRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameRepositoryImpl.class);

    @Override
    public Game save(Game game) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Saving game: {}", game.getName());
            entityManager.persist(game);
            transaction.commit();
            LOGGER.info("Game saved successfully");
            return game;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error saving game: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Game update(Game game) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Updating game: {}", game.getName());
            entityManager.merge(game);
            transaction.commit();
            LOGGER.info("Game updated successfully");
            return game;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error updating game: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Game> findAll() {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();

        try {
            LOGGER.info("Finding all games");
            return entityManager.createQuery("from Game", Game.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Game findById(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();

        try {
            LOGGER.info("Finding game with id: {}", id);
            return entityManager.find(Game.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Deleting game with id: {}", id);
            Game game = findById(id);
            if (game != null) {
                entityManager.remove(game);
                transaction.commit();
                LOGGER.info("Game deleted successfully");
            } else {
                LOGGER.warn("Game with id {} not found", id);
                transaction.rollback();
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting game: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
