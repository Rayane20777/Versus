package versus.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.model.Team;
import versus.service.interfaces.TeamService;

import java.util.List;

public class TeamController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerController.class);

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        LOGGER.info("Creating TeamController");
        this.teamService = teamService;
    }

    public Team getTeamById(long id) {
        LOGGER.info("Getting team with id: {}", id);
        return teamService.getTeamById(id);
    }

    public Team createTeam(Team team) {
        LOGGER.info("Creating new team: {}", team.getName());
        return teamService.createTeam(team);
    }

    public Team updateTeam(Team team) {
        LOGGER.info("Updating team: {}", team.getName());
        return teamService.updateTeam(team);
    }

    public void deleteTeam(long id) {
        LOGGER.info("Deleting team with id: {}", id);
        teamService.deleteTeam(id);
    }

    public List<Team> getAllTeams() {
        LOGGER.info("Getting all teams");
        return teamService.getAllTeams();
    }

    public boolean assignTeamToTournament(long teamId, long tournamentId) {
        LOGGER.info("Assigning team {} to tournament {}", teamId, tournamentId);
        return teamService.assignTeamToTournament(teamId, tournamentId);
    }

}
