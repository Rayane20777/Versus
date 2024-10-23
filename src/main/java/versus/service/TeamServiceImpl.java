package versus.service;

import org.springframework.transaction.annotation.Transactional;
import versus.model.Team;
import versus.repository.interfaces.TeamRepository;
import versus.service.interfaces.TeamService;

import java.util.List;


@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
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

}
