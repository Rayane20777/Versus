package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Game;
import versus.repository.interfaces.GameRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class GameRepositoryImpl implements GameRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public Game save(Game game) {
        LOGGER.info("Saving game: {}", game.getName());
        entityManager.getTransaction().begin();
        entityManager.persist(game);
        entityManager.getTransaction().commit();
        LOGGER.info("Game saved successfully");
        return game;
    }

    @Override
    public Game update(Game game) {
        LOGGER.info("Updating game: {}", game.getName());
        entityManager.getTransaction().begin();
        entityManager.merge(game);
        entityManager.getTransaction().commit();
        LOGGER.info("Game updated successfully");
        return game;
    }

    @Override
    public List<Game> findAll() {
        LOGGER.info("Finding all games");
        return entityManager.createQuery("from Game", Game.class).getResultList();

    }

    @Override
    public Game findById(long id) {
        LOGGER.info("Finding game with id: {}", id);
        return entityManager.find(Game.class, id);

    }

    @Override
    public void delete(long id) {
        LOGGER.info("Deleting game with id: {}", id);
        Game game = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(game);
        entityManager.getTransaction().commit();
        LOGGER.info("Game deleted successfully");
    }


}
