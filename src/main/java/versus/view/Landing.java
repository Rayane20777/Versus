package versus.view;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import versus.service.interfaces.PlayerService;
import versus.service.interfaces.TeamService;
import versus.service.interfaces.TournamentService;
import versus.controller.GameController;

import java.util.Scanner;

public class Landing {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static ApplicationContext context;
    private static Scanner scanner = new Scanner(System.in);

    // ANSI color codes
    private static final String PURPLE = "\u001B[35m";
    private static final String BLUE = "\u001B[34m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        PlayerService playerService = context.getBean(PlayerService.class);
        TeamService teamService = context.getBean(TeamService.class);
        TournamentService tournamentService = context.getBean(TournamentService.class);
        GameController gameController = context.getBean(GameController.class);

        boolean running = true;
        while (running) {
            displayLogo();
            displayMenu();
            int choice = readChoice();
            running = processChoice(choice, playerService, teamService, tournamentService, gameController);
        }

        logger.info("Application terminated.");
        scanner.close();
    }

    private static void displayLogo() {
        String logo = 
            PURPLE + "**      **  " + BLUE + "********   " + YELLOW + " ********  " + PURPLE + "**       ** " + BLUE + " ******** \n" +
            PURPLE + "**      **  " + BLUE + "**         " + YELLOW + "**         " + PURPLE + "**       ** " + BLUE + "**        \n" +
            PURPLE + "**      **  " + BLUE + "**         " + YELLOW + "**         " + PURPLE + "**       ** " + BLUE + "**        \n"+
            PURPLE + "**      **  " + BLUE + "******     " + YELLOW + "********   " + PURPLE + "**       ** " + BLUE + "********  \n" +
            PURPLE + " **    **   " + BLUE + "**         " + YELLOW + "       **   " + PURPLE + "**      ** " + BLUE + "        **\n" +
            PURPLE + "  **  **    " + BLUE + "**         " + YELLOW + "       **   " + PURPLE + "**      ** " + BLUE + "        **\n" +
            PURPLE + "   ****     " + BLUE + "********   " + YELLOW + "********   " + PURPLE + "  ********  " + BLUE + "********  \n" + RESET;
        
        System.out.println(logo);
    }



    private static void displayMenu() {
        logger.info("\n===== Main Menu =====");
        logger.info("1. Player Management");
        logger.info("2. Team Management");
        logger.info("3. Tournament Management");
        logger.info("4. Game Management");
        logger.info("5. Exit");
        logger.info("Enter your choice (1-5): ");
    }

    private static int readChoice() {
        while (!scanner.hasNextInt()) {
            logger.warn("Invalid input. Please enter a number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static boolean processChoice(int choice, PlayerService playerService, TeamService teamService, TournamentService tournamentService, GameController gameController) {
        switch (choice) {
            case 1:
                managePlayer(playerService);
                break;
            case 2:
                manageTeams(teamService);
                break;
            case 3:
                manageTournaments(tournamentService);
                break;
            case 4:
                manageGames(gameController);
                break;
            case 5:
                return false;
            default:
                logger.warn("Invalid choice. Please try again.");
        }
        return true;
    }

    private static void managePlayer(PlayerService playerService) {
        // Implement player management logic here
        logger.info("Player management...");
    }

    private static void manageTeams(TeamService teamService) {
        // Implement team management logic here
        logger.info("Team management...");
    }

    private static void manageTournaments(TournamentService tournamentService) {
        // Implement tournament management logic here
        logger.info("Tournament management...");
    }

    private static void manageGames(GameController gameController) {
        GameConsole gameConsole = new GameConsole(gameController);
        gameConsole.start();
    }
}
