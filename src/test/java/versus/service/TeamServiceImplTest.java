package versus.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import versus.model.Prize;
import versus.model.Team;
import versus.model.Tournament;
import versus.repository.interfaces.PrizeRepository;
import versus.repository.interfaces.TeamRepository;
import versus.repository.interfaces.TournamentRepository;
import versus.service.interfaces.TeamService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private PrizeRepository prizeRepository;

    private TeamService teamService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teamService = new TeamServiceImpl(teamRepository, tournamentRepository, prizeRepository);
    }

    @Test
    public void testCreateTeam() {
        Team team = new Team("Team A", 1000);
        when(teamRepository.createTeam(team)).thenReturn(team);

        Team result = teamService.createTeam(team);

        assertNotNull(result);
        assertEquals("Team A", result.getName());
        verify(teamRepository, times(1)).createTeam(team);
    }

    @Test
    public void testGetTeamById() {
        Team team = new Team("Team A", 1000);
        team.setId(1L);
        when(teamRepository.getTeamById(1L)).thenReturn(team);

        Team result = teamService.getTeamById(1L);

        assertNotNull(result);
        assertEquals(1L, (long) result.getId());
        assertEquals("Team A", result.getName());
        verify(teamRepository, times(1)).getTeamById(1L);
    }

    @Test
    public void testGetAllTeams() {
        Team team1 = new Team("Team A", 1000);
        Team team2 = new Team("Team B", 1200);
        List<Team> teams = Arrays.asList(team1, team2);
        when(teamRepository.getAllTeams()).thenReturn(teams);

        List<Team> result = teamService.getAllTeams();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(teamRepository, times(1)).getAllTeams();
    }

    @Test
    public void testUpdateTeam() {
        Team team = new Team("Team A", 1000);
        team.setId(1L);
        when(teamRepository.updateTeam(team)).thenReturn(team);

        Team result = teamService.updateTeam(team);

        assertNotNull(result);
        assertEquals("Team A", result.getName());
        verify(teamRepository, times(1)).updateTeam(team);
    }

    @Test
    public void testDeleteTeam() {
        teamService.deleteTeam(1L);
        verify(teamRepository, times(1)).deleteTeam(1L);
    }

    @Test
    public void testAssignTeamToTournament() {
        Team team = new Team("Team A", 1000);
        team.setId(1L);
        Tournament tournament = new Tournament();
        tournament.setId(1L);

        when(teamRepository.getTeamById(1L)).thenReturn(team);
        when(tournamentRepository.getTournamentById(1L)).thenReturn(tournament);
        when(teamRepository.updateTeam(team)).thenReturn(team);
        when(tournamentRepository.updateTournament(tournament)).thenReturn(tournament);

        boolean result = teamService.assignTeamToTournament(1L, 1L);

        assertTrue(result);
        assertEquals(tournament, team.getTournament());
        assertTrue(tournament.getTeams().contains(team));
        verify(teamRepository, times(1)).updateTeam(team);
        verify(tournamentRepository, times(1)).updateTournament(tournament);
    }

    @Test
    public void testAssignPrizeToTeam() {
        Team team = new Team("Team A", 1000);
        team.setId(1L);
        Prize prize = new Prize(1000, LocalDate.now(), "1st");

        when(teamRepository.getTeamById(1L)).thenReturn(team);
        when(prizeRepository.save(prize)).thenReturn(prize);
        when(teamRepository.updateTeam(team)).thenReturn(team);

        boolean result = teamService.assignPriceToTeam(1L, prize);

        assertTrue(result);
        assertEquals(prize, team.getPrize());
        verify(prizeRepository, times(1)).save(prize);
        verify(teamRepository, times(1)).updateTeam(team);
    }

    @Test
    public void testGetTeamsWithPrize() {
        Team team1 = new Team("Team A", 1000);
        Team team2 = new Team("Team B", 900);
        team2.setPrize(new Prize(500, LocalDate.now(), "2nd"));
        List<Team> allTeams = Arrays.asList(team1, team2);

        when(teamRepository.getAllTeams()).thenReturn(allTeams);

        List<Team> teamsWithPrize = teamService.getTeamHavingPrize();

        assertEquals(1, teamsWithPrize.size());
        assertEquals(team2, teamsWithPrize.get(0));
    }

    @Test
    public void testGetTeamsWithoutPrize() {
        Team team1 = new Team("Team A", 1000);
        Team team2 = new Team("Team B", 900);
        team2.setPrize(new Prize(500, LocalDate.now(), "2nd"));
        List<Team> allTeams = Arrays.asList(team1, team2);

        when(teamRepository.getAllTeams()).thenReturn(allTeams);

        List<Team> teamsWithoutPrize = teamService.getTeamNotHavingPrize();

        assertEquals(1, teamsWithoutPrize.size());
        assertEquals(team1, teamsWithoutPrize.get(0));
    }
}