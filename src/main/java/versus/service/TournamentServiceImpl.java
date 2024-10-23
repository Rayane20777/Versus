package versus.service;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Tournament;
import versus.service.interfaces.TournamentService;
import versus.repository.interfaces.TournamentRepository;

import java.util.List;

@Transactional
public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.createTournament(tournament);
    }

    @Override
    public Tournament updateTournament(Tournament tournament) {
        return tournamentRepository.updateTournament(tournament);
    }

    @Override
    public Tournament getTournamentById(long id) {
        return tournamentRepository.getTournamentById(id);
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
        return tournamentRepository.getAllTournaments().stream()
                .filter(tournament -> tournament.getId() == tournamentId)
                .findFirst()
                .map(Tournament::getEstimatedDuration)
                .orElse(0);
    }
}
