package versus.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import versus.model.Prize;
import versus.model.Team;
import versus.model.Tournament;
import versus.service.interfaces.TeamService;
import versus.service.interfaces.TournamentService;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class TeamServiceIntegrationTest {

    private TeamService teamService;
    private TournamentService tournamentService;
    private Team testTeam;
    private ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        teamService = context.getBean(TeamService.class);
        tournamentService = context.getBean(TournamentService.class);
        testTeam = new Team("Integration Test Team", 1000);
    }

    @Test
    public void testCreateAndRetrieveTeam() {
        Team createdTeam = teamService.createTeam(testTeam);
        assertNotNull(createdTeam.getId());

        Team retrievedTeam = teamService.getTeamById(createdTeam.getId());
        assertNotNull(retrievedTeam);
        assertEquals(createdTeam.getId(), retrievedTeam.getId());
        assertEquals(testTeam.getName(), retrievedTeam.getName());
        assertEquals(testTeam.getRanking(), retrievedTeam.getRanking());
    }

    @Test
    public void testUpdateTeam() {
        Team createdTeam = teamService.createTeam(testTeam);
        assertNotNull(createdTeam.getId());

        createdTeam.setName("Updated Team Name");
        createdTeam.setRanking(1500);

        Team updatedTeam = teamService.updateTeam(createdTeam);
        assertNotNull(updatedTeam);
        assertEquals(createdTeam.getId(), updatedTeam.getId());
        assertEquals("Updated Team Name", updatedTeam.getName());
        assertEquals(1500, updatedTeam.getRanking());
    }

    @Test
    public void testDeleteTeam() {
        Team createdTeam = teamService.createTeam(testTeam);
        assertNotNull(createdTeam.getId());

        flushAndClearEntityManager();

        teamService.deleteTeam(createdTeam.getId());

        Team deletedTeam = teamService.getTeamById(createdTeam.getId());
        assertNull(deletedTeam);
    }

    @Test
    public void testAssignPrizeToTeam() {
        Team createdTeam = runInTransaction(() -> teamService.createTeam(testTeam));
        assertNotNull(createdTeam.getId());

        Prize prize = new Prize(1000, LocalDate.now(), "1st");
        boolean success = runInTransaction(() -> teamService.assignPriceToTeam(createdTeam.getId(), prize));
        assertTrue(success);

        Team teamWithPrize = runInTransaction(() -> {
            Team team = teamService.getTeamById(createdTeam.getId());
            assertNotNull(team.getPrize());
            assertEquals(1000, team.getPrize().getAmount());
            assertEquals("1st", team.getPrize().getRank());
            return team;
        });
        assertNotNull(teamWithPrize);
    }



    private void flushAndClearEntityManager() {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.flush();
            entityManager.clear();
            transaction.commit();
        } finally {
            entityManager.close();
        }
    }

    private <T> T runInTransaction(Supplier<T> operation) {
        EntityManager entityManager = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T result = operation.get();
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
} 