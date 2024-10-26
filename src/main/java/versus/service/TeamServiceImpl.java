package versus.service;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Team;
import versus.model.Tournament;
import versus.repository.interfaces.TeamRepository;
import versus.repository.interfaces.TournamentRepository;
import versus.service.interfaces.TeamService;

import java.util.List;


@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;

    public TeamServiceImpl(TeamRepository teamRepository, TournamentRepository tournamentRepository) {
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Team createTeam(Team team) {
        return teamRepository.createTeam(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamRepository.updateTeam(team);
    }

    @Override
    public Team getTeamById(long id) {
        return teamRepository.getTeamById(id);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    @Override
    public void deleteTeam(long id) {
        teamRepository.deleteTeam(id);
    }

    @Override
    public boolean assignTeamToTournament(long teamId, long tournamentId) {
        Team team = teamRepository.getTeamById(teamId);
        Tournament tournament = tournamentRepository.getTournamentById(tournamentId);

        if (team != null && tournament != null) {
            team.setTournament(tournament);
            tournament.getTeams().add(team);
            teamRepository.updateTeam(team);
            tournamentRepository.updateTournament(tournament);
            return true;
        }
        return false;
    }

}
