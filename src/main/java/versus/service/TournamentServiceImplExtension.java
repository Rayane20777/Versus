package versus.service;

import versus.model.Game;
import versus.model.Tournament;
import versus.repository.interfaces.GameRepository;
import versus.repository.interfaces.TournamentRepository;
import versus.service.interfaces.TournamentService;

public class TournamentServiceImplExtension extends TournamentServiceImpl {

    public TournamentServiceImplExtension(TournamentRepository tournamentRepository, GameRepository gameRepository) {
        super(tournamentRepository, gameRepository);
    }

    @Override
    public int calculateEstimatedDuration(long tournamentId) {
        Tournament tournament = getTournamentById(tournamentId);
        if (tournament == null || tournament.getGame() == null) {
            return 0;
        }

        Game game = tournament.getGame();
        int numberOfTeams = tournament.getTeams().size();
        int averageMatchDuration = game.getAverageMatchDuration();
        int gameDifficulty = game.getDifficulty();
        int matchBreakTime = tournament.getMatchBreakTime();
        int ceremonyTime = tournament.getCeremonyTime();

        int totalMatchTime = numberOfTeams * averageMatchDuration * gameDifficulty;
        int totalBreakTime = (numberOfTeams - 1) * matchBreakTime;
        
        return totalMatchTime + totalBreakTime + ceremonyTime;
    }
}