package versus.service;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Game;
import versus.model.Tournament;
import versus.service.interfaces.TournamentService;
import versus.repository.interfaces.TournamentRepository;
import versus.repository.interfaces.GameRepository;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Date;

@Transactional
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository, GameRepository gameRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament createTournament(Tournament tournament) {
        if (tournament.getGame() == null) {
            throw new IllegalArgumentException("A tournament must have a game");
        }
        return tournamentRepository.createTournament(tournament);
    }

    @Override
    public Tournament updateTournament(Tournament tournament) {
        if (tournament.getGame() == null) {
            throw new IllegalArgumentException("A tournament must have a game");
        }
        return tournamentRepository.updateTournament(tournament);
    }

    @Override
    public Tournament getTournamentById(long id) {
        Tournament tournament = tournamentRepository.getTournamentById(id);
        if (tournament != null && tournament.getGame() != null) {
            Hibernate.initialize(tournament.getGame());
        }
        return tournament;
    }

    @Override
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.getAllTournaments();
    }

    @Override
    public void deleteTournament(long id) {
        tournamentRepository.deleteTournament(id);
    }

    @Override
    public int calculateEstimatedDuration(long tournamentId) {
        throw new UnsupportedOperationException("calculateEstimatedDuration is not implemented in TournamentServiceImpl");
    }
}
