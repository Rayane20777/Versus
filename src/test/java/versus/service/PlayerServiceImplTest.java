package versus.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import versus.model.Player;
import versus.repository.interfaces.PlayerRepository;
import versus.service.interfaces.PlayerService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    private PlayerService playerService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        playerService = new PlayerServiceImpl(playerRepository);
    }

    @Test
    public void testCreatePlayer() {
        Player player = new Player("John Doe", "johnd", 25);
        when(playerRepository.createPlayer(player)).thenReturn(player);

        Player result = playerService.createPlayer(player);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(playerRepository, times(1)).createPlayer(player);
    }

    @Test
    public void testGetPlayerById() {
        Player player = new Player("John Doe", "johnd", 25);
        player.setId(1L);
        when(playerRepository.getPlayerById(1L)).thenReturn(player);

        Player result = playerService.getPlayerById(1L);

        assertNotNull(result);
        assertEquals(1L, (long) result.getId());
        assertEquals("John Doe", result.getName());
        verify(playerRepository, times(1)).getPlayerById(1L);
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player("John Doe", "johnd", 25);
        Player player2 = new Player("Jane Doe", "janed", 28);
        List<Player> players = Arrays.asList(player1, player2);
        when(playerRepository.getAllPlayers()).thenReturn(players);

        List<Player> result = playerService.getAllPlayers();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(playerRepository, times(1)).getAllPlayers();
    }

    @Test
    public void testUpdatePlayer() {
        Player player = new Player("John Doe", "johnd", 25);
        player.setId(1L);
        when(playerRepository.updatePlayer(player)).thenReturn(player);

        Player result = playerService.updatePlayer(player);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(playerRepository, times(1)).updatePlayer(player);
    }

    @Test
    public void testDeletePlayer() {
        playerService.deletePlayer(1L);
        verify(playerRepository, times(1)).deletePlayer(1L);
    }
}