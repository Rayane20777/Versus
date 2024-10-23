package versus.repository.interfaces;

import versus.model.Tournament;

import java.util.List;

public interface TournamentRepository {
    Tournament getTournamentById(long id);
    List<Tournament> getAllTournaments();
    Tournament createTournament(Tournament Tournament);
    Tournament updateTournament(Tournament Tournament);
    void deleteTournament(long id);
}
