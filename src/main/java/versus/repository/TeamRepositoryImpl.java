package versus.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Team;
import versus.repository.interfaces.TeamRepository;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class TeamRepositoryImpl implements TeamRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerRepositoryImpl.class);



    @Override
    public Team createTeam(Team Team) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Saving Team: {}", Team.getName());
            entityManager.persist(Team);
            transaction.commit();
            LOGGER.info("Team saved successfully with info: {}", Team);
            return Team;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error saving Team: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Team updateTeam(Team Team) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Updating Team: {}", Team.getName());
            entityManager.merge(Team);
            transaction.commit();
            LOGGER.info("Team updated successfully with info: {}", Team);
            return Team;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error updating Team: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Team> getAllTeams() {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        try {
            LOGGER.info("Finding all Teams");
            return entityManager.createQuery("SELECT t FROM Team t", Team.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Team getTeamById(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        try {
            LOGGER.info("Finding Team with id: {}", id);
            return entityManager.find(Team.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteTeam(long id) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            LOGGER.info("Deleting Team with id: {}", id);
            Team Team = entityManager.find(Team.class, id);
            if (Team != null) {
                entityManager.remove(Team);
            }
            transaction.commit();
            LOGGER.info("Team deleted successfully");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            LOGGER.error("Error deleting Team: {}", e.getMessage());
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
