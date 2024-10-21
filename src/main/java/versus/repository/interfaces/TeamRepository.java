package versus.repository.interfaces;

import versus.model.Team;

import java.util.List;

public interface TeamRepository {
    Team getTeamById(int id);
    List<Team> getAllTeams();
    Team createTeam(Team team);
    Team updateTeam(Team team);
    void deleteTeam(int id);
}
