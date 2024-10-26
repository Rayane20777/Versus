package versus.service.interfaces;

import versus.model.Team;
import java.util.List;

public interface TeamService {
    Team getTeamById(long id);
    List<Team> getAllTeams();
    Team createTeam(Team team);
    Team updateTeam(Team team);
    void deleteTeam(long id);
    boolean assignTeamToTournament(long teamId, long tournamentId);

}
