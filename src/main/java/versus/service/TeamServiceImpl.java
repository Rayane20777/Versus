package versus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import versus.model.Prize;
import versus.model.Team;
import versus.model.Tournament;
import versus.repository.PlayerRepositoryImpl;
import versus.repository.interfaces.TeamRepository;
import versus.repository.interfaces.TournamentRepository;
import versus.repository.interfaces.PrizeRepository;
import versus.service.interfaces.TeamService;

import java.util.List;
import java.util.stream.Collectors;


@Transactional
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TournamentRepository tournamentRepository;
    private final PrizeRepository prizeRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeamServiceImpl.class);

    public TeamServiceImpl(TeamRepository teamRepository, TournamentRepository tournamentRepository, PrizeRepository prizeRepository) {
        this.teamRepository = teamRepository;
        this.tournamentRepository = tournamentRepository;
        this.prizeRepository = prizeRepository;
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

    @Override
    public boolean assignPriceToTeam(Long teamId, Prize prize) {
        try {
            Team team = teamRepository.getTeamById(teamId);
            if (team != null) {
                Prize savedPrize = prizeRepository.save(prize);
                team.setPrize(savedPrize);
                teamRepository.updateTeam(team);
                return true;
            }
            return false;
        } catch (Exception e) {
            LOGGER.error("Error assigning prize to team: {}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Team> getTeamHavingPrize() {
        List<Team> teams = teamRepository.getAllTeams();
        return teams.stream().filter(team -> team.getPrize() != null).collect(Collectors.toList());
    }

    @Override
    public List<Team> getTeamNotHavingPrize() {
        List<Team> teams = teamRepository.getAllTeams();
        return teams.stream().filter(team -> team.getPrize() == null).collect(Collectors.toList());
    }



}
