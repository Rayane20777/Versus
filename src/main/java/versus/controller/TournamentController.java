package versus.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.model.Tournament;
import versus.service.interfaces.TournamentService;

import java.util.List;

public class TournamentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        LOGGER.info("Creating TournamentController");
        this.tournamentService = tournamentService;
    }

    public Tournament getTournamentById(long id) {
        LOGGER.info("Getting tournament with id: {}", id);
        return tournamentService.getTournamentById(id);
    }

    public Tournament createTournament(Tournament tournament) {
        LOGGER.info("Creating new tournament: {}");
        return tournamentService.createTournament(tournament);
    }

    public Tournament updateTournament(Tournament tournament) {
        LOGGER.info("Updating tournament: {}");
        return tournamentService.updateTournament(tournament);
    }

    public void deleteTournament(long id) {
        LOGGER.info("Deleting tournament with id: {}", id);
        tournamentService.deleteTournament(id);
    }

    public List<Tournament> getAllTournaments() {
        LOGGER.info("Getting all tournaments");
        return tournamentService.getAllTournaments();
    }

}
