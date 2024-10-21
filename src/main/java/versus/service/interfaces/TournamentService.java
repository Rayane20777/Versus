package versus.service.interfaces;

import versus.model.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament createTournament(Tournament tournament);
    Tournament getTournamentById(int id);
    Tournament updateTournament(Tournament tournament);
    List<Tournament> getAllTournaments();
    void deleteTournament(int id);
    int calculateEstimatedDuration(int tournamentId);

}
