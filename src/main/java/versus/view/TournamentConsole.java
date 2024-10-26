package versus.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import versus.controller.GameController;
import versus.controller.TournamentController;
import versus.model.Game;
import versus.model.Tournament;
import versus.model.enums.TournamentStatus;

import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class TournamentConsole {
    private static final Logger logger = LoggerFactory.getLogger(TournamentConsole.class);
    private final TournamentController tournamentController;
    private final Scanner scanner;
    private final GameController gameController;

    public TournamentConsole(TournamentController tournamentController, GameController gameController) {
        this.tournamentController = tournamentController;
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
        logger.info("\n===== Tournament Management =====");
        logger.info("1. Create a new tournament");
        logger.info("2. View all tournaments");
        logger.info("3. View tournament by ID");
        logger.info("4. Update a tournament");
        logger.info("5. Delete a tournament");
        logger.info("6. Calculate estimated duration");
        logger.info("7. Return to main menu");
        logger.info("Enter your choice (1-7): ");
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
                createTournament();
                break;
            case 2:
                viewAllTournaments();
                break;
            case 3:
                viewTournamentById();
                break;
            case 4:
                updateTournament();
                break;
            case 5:
                deleteTournament();
                break;
            case 6:
                calculateEstimatedDuration();
                break;
            case 7:
                return false;
            default:
                logger.warn("Invalid choice. Please try again.");
        }
        return true;
    }

    private void createTournament() {
        logger.info("Enter tournament title: ");
        scanner.nextLine(); // Consume newline
        String title = scanner.nextLine();

        logger.info("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.next();
        Date startDate = java.sql.Date.valueOf(startDateStr);

        logger.info("Enter end date (yyyy-MM-dd): ");
        String endDateStr = scanner.next();
        Date endDate = java.sql.Date.valueOf(endDateStr);

        logger.info("Enter spectator count: ");
        int spectatorCount = scanner.nextInt();

        logger.info("Enter estimated duration (in minutes): ");
        int estimatedDuration = scanner.nextInt();

        logger.info("Enter match break time (in minutes): ");
        int matchBreakTime = scanner.nextInt();

        logger.info("Enter ceremony time (in minutes): ");
        int ceremonyTime = scanner.nextInt();

        logger.info("Enter game ID: ");
        long gameId = scanner.nextLong();
        Game game = gameController.getGameById(gameId);

        if (game == null) {
            logger.info("Game not found. Tournament creation cancelled.");
            return;
        }

        Tournament newTournament = new Tournament(title, game, startDate, endDate, spectatorCount, estimatedDuration, matchBreakTime, ceremonyTime, TournamentStatus.IN_PREPARATION);
        newTournament.setGame(game);  // Explicitly set the game
        Tournament createdTournament = tournamentController.createTournament(newTournament);
        logger.info("Tournament created successfully. ID: " + createdTournament.getId());
    }

    private void viewAllTournaments() {
        List<Tournament> tournaments = tournamentController.getAllTournaments();
        if (tournaments.isEmpty()) {
            logger.info("No tournaments found.");
        } else {
            for (Tournament tournament : tournaments) {
                displayTournament(tournament);
            }
        }
    }

    private void viewTournamentById() {
        logger.info("Enter tournament ID: ");
        long id = scanner.nextLong();
        Tournament tournament = tournamentController.getTournamentById(id);
        if (tournament != null) {
            displayTournament(tournament);
        } else {
            logger.info("Tournament not found.");
        }
    }

    private void updateTournament() {
        logger.info("Enter tournament ID to update: ");
        long id = scanner.nextLong();
        Tournament tournament = tournamentController.getTournamentById(id);
        if (tournament != null) {
            logger.info("Enter new title (or press enter to keep current): ");
            scanner.nextLine(); // Consume newline
            String title = scanner.nextLine();
            if (!title.isEmpty()) {
                tournament.setTitle(title);
            }

            logger.info("Enter new start date (yyyy-MM-dd) (or press enter to keep current): ");
            String startDateStr = scanner.nextLine();
            if (!startDateStr.isEmpty()) {
                tournament.setStartDate(java.sql.Date.valueOf(startDateStr));
            }

            logger.info("Enter new end date (yyyy-MM-dd) (or press enter to keep current): ");
            String endDateStr = scanner.nextLine();
            if (!endDateStr.isEmpty()) {
                tournament.setEndDate(java.sql.Date.valueOf(endDateStr));
            }

            logger.info("Enter new spectator count (or -1 to keep current): ");
            int spectatorCount = scanner.nextInt();
            if (spectatorCount != -1) {
                tournament.setSpectatorCount(spectatorCount);
            }

            Tournament updatedTournament = tournamentController.updateTournament(tournament);
            logger.info("Tournament updated successfully.");
            displayTournament(updatedTournament);
        } else {
            logger.info("Tournament not found.");
        }
    }

    private void deleteTournament() {
        logger.info("Enter tournament ID to delete: ");
        long id = scanner.nextLong();
        Tournament tournament = tournamentController.getTournamentById(id);
        if (tournament != null) {
            tournamentController.deleteTournament(id);
            logger.info("Tournament deleted successfully.");
        } else {
            logger.info("Tournament not found.");
        }
    }

    private void calculateEstimatedDuration() {
        logger.info("Enter tournament ID to calculate estimated duration: ");
        long id = scanner.nextLong();
        int duration = tournamentController.calculateEstimatedDuration(id);
        if (duration > 0) {
            int hours = duration / 60;
            int minutes = duration % 60;
            logger.info("Estimated duration for tournament " + id + ": " + hours + " hours and " + minutes + " minutes");
        } else {
            logger.info("Tournament not found or unable to calculate duration.");
        }
    }

    private void displayTournament(Tournament tournament) {
        logger.info("Tournament ID: " + tournament.getId());
        logger.info("Title: " + tournament.getTitle());
        logger.info("Start Date: " + tournament.getStartDate());
        logger.info("End Date: " + tournament.getEndDate());
        logger.info("Spectator Count: " + tournament.getSpectatorCount());
        logger.info("Estimated Duration: " + tournament.getEstimatedDuration() + " minutes");
        logger.info("Match Break Time: " + tournament.getMatchBreakTime() + " minutes");
        logger.info("Ceremony Time: " + tournament.getCeremonyTime() + " minutes");
        logger.info("Status: " + tournament.getStatus());
        logger.info("--------------------");
    }
}
