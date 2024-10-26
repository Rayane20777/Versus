package versus.integration;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import versus.model.Game;
import versus.service.interfaces.GameService;
import versus.util.EntityManagerUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import java.util.List;

import static org.junit.Assert.*;

public class GameServiceIntegrationTest {

    private GameService gameService;
    private Game testGame;
    private ApplicationContext context;

    @Before
    public void setUp() {
        context = new ClassPathXmlApplicationContext("applicationContext-test.xml");
        gameService = context.getBean(GameService.class);
        testGame = new Game("Integration Test Game", 6, 40);
    }

    @Test
    public void testCreateAndRetrieveGame() {
        Game createdGame = gameService.createGame(testGame);
        assertNotNull(createdGame.getId());

        Game retrievedGame = gameService.getGameById(createdGame.getId());
        assertNotNull(retrievedGame);
        assertEquals(createdGame.getId(), retrievedGame.getId());
        assertEquals(testGame.getName(), retrievedGame.getName());
        assertEquals(testGame.getDifficulty(), retrievedGame.getDifficulty());
        assertEquals(testGame.getAverageMatchDuration(), retrievedGame.getAverageMatchDuration());
    }

    @Test
    public void testUpdateGame() {
        Game createdGame = gameService.createGame(testGame);
        assertNotNull(createdGame.getId());

        createdGame.setName("Updated Game Name");
        createdGame.setDifficulty(8);

        Game updatedGame = gameService.updateGame(createdGame);
        assertNotNull(updatedGame);
        assertEquals(createdGame.getId(), updatedGame.getId());
        assertEquals("Updated Game Name", updatedGame.getName());
        assertEquals(8, updatedGame.getDifficulty());
    }

    @Test
    public void testDeleteGame() {
        Game createdGame = gameService.createGame(testGame);
        assertNotNull(createdGame.getId());

        // Flush and clear the entity manager to ensure the game is persisted
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

        gameService.deleteGame(createdGame.getId());

        Game deletedGame = gameService.getGameById(createdGame.getId());
        assertNull(deletedGame);
    }

    @Test
    public void testGetAllGames() {
        Game game1 = gameService.createGame(new Game("Game 1", 5, 30));
        Game game2 = gameService.createGame(new Game("Game 2", 7, 45));

        List<Game> allGames = gameService.getAllGames();
        assertNotNull(allGames);
        assertTrue(allGames.size() >= 2);
        assertTrue(allGames.stream().anyMatch(g -> g.getId().equals(game1.getId())));
        assertTrue(allGames.stream().anyMatch(g -> g.getId().equals(game2.getId())));
    }
}
