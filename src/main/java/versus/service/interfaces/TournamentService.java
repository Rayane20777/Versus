package versus.service.interfaces;

import versus.model.Tournament;

import java.util.List;

public interface TournamentService {
    Tournament createTournament(Tournament tournament);
    Tournament getTournamentById(long id);
    Tournament updateTournament(Tournament tournament);
    List<Tournament> getAllTournaments();
    void deleteTournament(long id);
    int calculateEstimatedDuration(long tournamentId);


}
