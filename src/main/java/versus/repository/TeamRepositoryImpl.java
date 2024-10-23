package versus.repository;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Team;
import versus.repository.interfaces.TeamRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class TeamRepositoryImpl implements TeamRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Team createTeam(Team Team) {
        entityManager.getTransaction().begin();
        entityManager.persist(Team);
        entityManager.getTransaction().commit();
        return Team;
    }

    @Override
    public Team updateTeam(Team Team) {
        entityManager.getTransaction().begin();
        entityManager.merge(Team);
        entityManager.getTransaction().commit();
        return Team;
    }

    @Override
    public List<Team> getAllTeams() {
        return entityManager.createQuery("from Team", Team.class).getResultList();
    }

    @Override
    public Team getTeamById(long id) {
        return entityManager.find(Team.class, id);
    }

    @Override
    public void deleteTeam(long id) {
        Team Team = getTeamById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(Team);
        entityManager.getTransaction().commit();
    }
}
