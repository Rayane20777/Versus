package versus.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.controller.GameController;
import versus.model.Game;

import java.util.List;
import java.util.Scanner;

public class GameConsole {
    private static final Logger logger = LoggerFactory.getLogger(GameConsole.class);
    private final GameController gameController;
    private final Scanner scanner;

    public GameConsole(GameController gameController) {
        this.gameController = gameController;
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
        logger.info("\n===== Game Management =====");
        logger.info("1. Create a new game");
        logger.info("2. View all games");
        logger.info("3. View game by ID");
        logger.info("4. Update a game");
        logger.info("5. Delete a game");
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
                createGame();
                break;
            case 2:
                viewAllGames();
                break;
            case 3:
                viewGameById();
                break;
            case 4:
                updateGame();
                break;
            case 5:
                deleteGame();
                break;
            case 6:
                return false;
            default:
                logger.warn("Invalid choice. Please try again.");
        }
        return true;
    }

    private void createGame() {
        logger.info("Enter game name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        logger.info("Enter game difficulty (1-10): ");
        int difficulty = scanner.nextInt();

        logger.info("Enter average match duration (in minutes): ");
        int averageMatchDuration = scanner.nextInt();

        Game newGame = new Game(name, difficulty, averageMatchDuration);
        Game createdGame = gameController.createGame(newGame);
        logger.info("Game created successfully. ID: " + createdGame.getId());
    }

    private void viewAllGames() {
        List<Game> games = gameController.getAllGames();
        if (games.isEmpty()) {
            logger.info("No games found.");
        } else {
            for (Game game : games) {
                displayGame(game);
            }
        }
    }

    private void viewGameById() {
        logger.info("Enter game ID: ");
        long id = scanner.nextLong();
        Game game = gameController.getGameById(id);
        if (game != null) {
            displayGame(game);
        } else {
            logger.info("Game not found.");
        }
    }

    private void updateGame() {
        logger.info("Enter game ID to update: ");
        long id = scanner.nextLong();
        Game game = gameController.getGameById(id);
        if (game != null) {
            logger.info("Enter new name (or press enter to keep current): ");
            scanner.nextLine(); // Consume newline
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                game.setName(name);
            }

            logger.info("Enter new difficulty (1-10) (or -1 to keep current): ");
            int difficulty = scanner.nextInt();
            if (difficulty != -1) {
                game.setDifficulty(difficulty);
            }

            logger.info("Enter new average match duration (or -1 to keep current): ");
            int averageMatchDuration = scanner.nextInt();
            if (averageMatchDuration != -1) {
                game.setAverageMatchDuration(averageMatchDuration);
            }

            Game updatedGame = gameController.updateGame(game);
            logger.info("Game updated successfully.");
            displayGame(updatedGame);
        } else {
            logger.info("Game not found.");
        }
    }

    private void deleteGame() {
        logger.info("Enter game ID to delete: ");
        long id = scanner.nextLong();
        Game game = gameController.getGameById(id);
        if (game != null) {
            gameController.deleteGame(id);
            logger.info("Game deleted successfully.");
        } else {
            logger.info("Game not found.");
        }
    }

    private void displayGame(Game game) {
        logger.info("Game ID: " + game.getId());
        logger.info("Name: " + game.getName());
        logger.info("Difficulty: " + game.getDifficulty());
        logger.info("Average Match Duration: " + game.getAverageMatchDuration() + " minutes");
        logger.info("--------------------");
    }
}
