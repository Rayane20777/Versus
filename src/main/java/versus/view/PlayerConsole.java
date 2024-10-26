package versus.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.controller.PlayerController;
import versus.controller.TeamController;
import versus.model.Player;
import versus.model.Team;

import java.util.List;
import java.util.Scanner;

public class PlayerConsole {
    private static final Logger logger = LoggerFactory.getLogger(PlayerConsole.class);
    private final PlayerController playerController;
    private final TeamController teamController;
    private final Scanner scanner;

    public PlayerConsole(PlayerController playerController, TeamController teamController) {
        this.playerController = playerController;
        this.teamController = teamController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readChoice();
            running = processChoice(choice);
        }
    }

    private void displayMenu() {
        logger.info("\n===== Player Management =====");
        logger.info("1. Create a new player");
        logger.info("2. View player information");
        logger.info("3. Update player information");
        logger.info("4. Delete player");
        logger.info("5. Choose a team");
        logger.info("6. Return to main menu");
        logger.info("Enter your choice (1-6): ");
    }

    private int readChoice() {
        while (!scanner.hasNextInt()) {
            logger.warn("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private boolean processChoice(int choice) {
        switch (choice) {
            case 1:
                createPlayer();
                break;
            case 2:
                viewPlayerInformation();
                break;
            case 3:
                updatePlayerInformation();
                break;
            case 4:
                deletePlayer();
                break;
            case 5:
                chooseTeam();
                break;
            case 6:
                return false;
            default:
                logger.warn("Invalid choice. Please try again.");
        }
        return true;
    }

    private void createPlayer() {
        logger.info("Enter player name: ");
        scanner.nextLine();
        String name = scanner.nextLine();

        logger.info("Enter in-game name: ");
        String inGameName = scanner.nextLine();

        logger.info("Enter age: ");
        int age = scanner.nextInt();

        Player newPlayer = new Player(name, inGameName, age);
        Player createdPlayer = playerController.createPlayer(newPlayer);
        logger.info("Player created successfully. ID: " + createdPlayer.getId());
    }

    private void viewPlayerInformation() {
        logger.info("Enter player ID: ");
        long id = scanner.nextLong();

        Player player = playerController.getPlayerById(id);
        if (player != null) {
            displayPlayer(player);
        } else {
            logger.info("Player not found.");
        }
    }

    private void updatePlayerInformation() {
        logger.info("Enter player ID: ");
        long id = scanner.nextLong();

        Player player = playerController.getPlayerById(id);
        if (player != null) {
            logger.info("Enter new name (or press enter to keep current): ");
            scanner.nextLine();
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                player.setName(name);
            }

            logger.info("Enter new in-game name (or press enter to keep current): ");
            String inGameName = scanner.nextLine();
            if (!inGameName.isEmpty()) {
                player.setInGameName(inGameName);
            }

            logger.info("Enter new age (or -1 to keep current): ");
            int age = scanner.nextInt();
            if (age != -1) {
                player.setAge(age);
            }

            Player updatedPlayer = playerController.updatePlayer(player);
            logger.info("Player information updated successfully.");
            displayPlayer(updatedPlayer);
        } else {
            logger.info("Player not found.");
        }
    }

    private void deletePlayer() {
        logger.info("Enter player ID to delete: ");
        long id = scanner.nextLong();

        Player player = playerController.getPlayerById(id);
        if (player != null) {
            playerController.deletePlayer(id);
            logger.info("Player deleted successfully.");
        } else {
            logger.info("Player not found.");
        }
    }

    private void chooseTeam() {
        logger.info("Enter player ID: ");
        long playerId = scanner.nextLong();

        Player player = playerController.getPlayerById(playerId);
        if (player != null) {
            List<Team> teams = teamController.getAllTeams();
            logger.info("Available teams:");
            for (Team team : teams) {
                logger.info(team.getId() + ": " + team.getName());
            }

            logger.info("Enter team ID to join: ");
            long teamId = scanner.nextLong();

            Player updatedPlayer = playerController.chooseTeam(playerId, teamId);
            if (updatedPlayer != null) {
                logger.info("Player joined team successfully.");
                displayPlayer(updatedPlayer);
            } else {
                logger.info("Failed to join team. Please try again.");
            }
        } else {
            logger.info("Player not found.");
        }
    }

    private void displayPlayer(Player player) {
        logger.info("Player ID: " + player.getId());
        logger.info("Name: " + player.getName());
        logger.info("In-game name: " + player.getInGameName());
        logger.info("Age: " + player.getAge());
        logger.info("Team: " + (player.getTeam() != null ? player.getTeam().getName() : "No team"));
        logger.info("--------------------");
    }
}
