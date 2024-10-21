package versus.service;

import versus.service.interfaces.TournamentService;
import versus.repository.interfaces.TournamentRepository;

public class TournamentServiceImpl implements TournamentService {
    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }
}
