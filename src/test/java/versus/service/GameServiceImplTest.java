package versus.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import versus.model.Game;
import versus.repository.interfaces.GameRepository;
import versus.service.interfaces.GameService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameServiceImplTest {

    @Mock
    private GameRepository gameRepository;

    private GameService gameService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        gameService = new GameServiceImpl(gameRepository);
    }

    @Test
    public void testCreateGame() {
        Game game = new Game("Test Game", 5, 30);
        when(gameRepository.save(game)).thenReturn(game);

        Game result = gameService.createGame(game);

        assertNotNull(result);
        assertEquals("Test Game", result.getName());
        verify(gameRepository, times(1)).save(game);
    }

    @Test
    public void testGetGameById() {
        Game game = new Game("Test Game", 5, 30);
        game.setId(1L);
        when(gameRepository.findById(1L)).thenReturn(game);

        Game result = gameService.getGameById(1L);

        assertNotNull(result);
        assertEquals(1L, (long) result.getId());
        assertEquals("Test Game", result.getName());
        verify(gameRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllGames() {
        Game game1 = new Game("Game 1", 5, 30);
        Game game2 = new Game("Game 2", 7, 45);
        List<Game> games = Arrays.asList(game1, game2);
        when(gameRepository.findAll()).thenReturn(games);

        List<Game> result = gameService.getAllGames();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(gameRepository, times(1)).findAll();
    }

    @Test
    public void testUpdateGame() {
        Game game = new Game("Test Game", 5, 30);
        game.setId(1L);
        when(gameRepository.update(game)).thenReturn(game);

        Game result = gameService.updateGame(game);

        assertNotNull(result);
        assertEquals("Test Game", result.getName());
        verify(gameRepository, times(1)).update(game);
    }

    @Test
    public void testDeleteGame() {
        gameService.deleteGame(1L);
        verify(gameRepository, times(1)).delete(1L);
    }
}
