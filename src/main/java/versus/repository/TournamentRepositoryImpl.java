package versus.repository;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Tournament;
import versus.repository.interfaces.TournamentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
public class TournamentRepositoryImpl implements TournamentRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Tournament createTournament(Tournament Tournament) {
        entityManager.getTransaction().begin();
        entityManager.persist(Tournament);
        entityManager.getTransaction().commit();
        return Tournament;
    }

    @Override
    public Tournament updateTournament(Tournament Tournament) {
        entityManager.getTransaction().begin();
        entityManager.merge(Tournament);
        entityManager.getTransaction().commit();
        return Tournament;
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return entityManager.createQuery("from Tournament", Tournament.class).getResultList();
    }

    @Override
    public Tournament getTournamentById(long id) {
        return entityManager.find(Tournament.class, id);
    }

    @Override
    public void deleteTournament(long id) {
        Tournament Tournament = getTournamentById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(Tournament);
        entityManager.getTransaction().commit();
    }
}
