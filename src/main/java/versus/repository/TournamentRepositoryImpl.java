package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Tournament;
import versus.repository.interfaces.TournamentRepository;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class TournamentRepositoryImpl implements TournamentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);


    @Override
    public Tournament createTournament(Tournament Tournament) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Saving Tournament: {}");
            entityManager.persist(Tournament);
            transaction.commit();
            LOGGER.info("Tournament saved successfully with info: {}", Tournament);
            return Tournament;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error saving Tournament: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tournament updateTournament(Tournament Tournament) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Updating Tournament: {}");
            entityManager.merge(Tournament);
            transaction.commit();
            LOGGER.info("Tournament updated successfully with info: {}", Tournament);
            return Tournament;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error updating Tournament: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Tournament> getAllTournaments() {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        try {
            LOGGER.info("Finding all Tournaments");
            return entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList();
        } catch (Exception e) {
            LOGGER.error("Error finding all Tournaments: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Tournament getTournamentById(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        try {
            LOGGER.info("Finding Tournament with id: {}", id);
            return entityManager.find(Tournament.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteTournament(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Deleting Tournament with id: {}", id);
            Tournament Tournament = getTournamentById(id);
            if (Tournament != null) {
                entityManager.remove(Tournament);
            }
            transaction.commit();
            LOGGER.info("Tournament deleted successfully");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting Tournament: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
