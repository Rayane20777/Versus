package versus.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import versus.controller.TeamController;
import versus.model.Prize;
import versus.model.Team;

import java.util.List;
import java.util.Scanner;

public class TeamConsole {
    private static final Logger logger = LoggerFactory.getLogger(TeamConsole.class);
    private final TeamController teamController;
    private final Scanner scanner;
    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

    public TeamConsole(TeamController teamController) {
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
        logger.info("\n===== Team Management =====");
        logger.info("1. Create a new team");
        logger.info("2. View team information");
        logger.info("3. Update team information");
        logger.info("4. Delete team");
        logger.info("5. View all teams");
        logger.info("6. Assign team to tournament");
        logger.info("7. Assign prize to team");
        logger.info("8. View teams with prizes");
        logger.info("9. View teams without prizes");
        logger.info("10. Return to main menu");
        logger.info("Enter your choice (1-8): ");
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
                createTeam();
                break;
            case 2:
                viewTeamInformation();
                break;
            case 3:
                updateTeamInformation();
                break;
            case 4:
                deleteTeam();
                break;
            case 5:
                viewAllTeams();
                break;
            case 6:
                assignTeamToTournament();
                break;
            case 7:
                assignPrizeToTeam();
                break;
            case 8:
                viewTeamsWithPrizes();
                break;
            case 9:
                wiewPrizelessTeams();
                break;
            case 10:
                return false;
            default:
                logger.warn("Invalid choice. Please try again.");
        }
        return true;
    }

    private void createTeam() {
        logger.info("Enter team name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();

        logger.info("Enter team ranking: ");
        int ranking = scanner.nextInt();

        Team newTeam = new Team(name, ranking);
        newTeam.setRanking(ranking);
        Team createdTeam = teamController.createTeam(newTeam);
        logger.info("Team created successfully. ID: " + createdTeam.getId());
    }

    private void viewTeamInformation() {
        logger.info("Enter team ID: ");
        long id = scanner.nextLong();

        Team team = teamController.getTeamById(id);
        if (team != null) {
            displayTeam(team);
        } else {
            logger.info("Team not found.");
        }
    }

    private void updateTeamInformation() {
        logger.info("Enter team ID: ");
        long id = scanner.nextLong();

        Team team = teamController.getTeamById(id);
        if (team != null) {
            logger.info("Enter new name (or press enter to keep current): ");
            scanner.nextLine(); // Consume newline
            String name = scanner.nextLine();
            if (!name.isEmpty()) {
                team.setName(name);
            }

            logger.info("Enter new ranking (or -1 to keep current): ");
            int ranking = scanner.nextInt();
            if (ranking != -1) {
                team.setRanking(ranking);
            }

            Team updatedTeam = teamController.updateTeam(team);
            logger.info("Team information updated successfully.");
            displayTeam(updatedTeam);
        } else {
            logger.info("Team not found.");
        }
    }

    private void deleteTeam() {
        logger.info("Enter team ID to delete: ");
        long id = scanner.nextLong();

        Team team = teamController.getTeamById(id);
        if (team != null) {
            teamController.deleteTeam(id);
            logger.info("Team deleted successfully.");
        } else {
            logger.info("Team not found.");
        }
    }

    private void viewAllTeams() {
        List<Team> teams = teamController.getAllTeams();
        if (teams.isEmpty()) {
            logger.info("No teams found.");
        } else {
            logger.info("All teams:");
            for (Team team : teams) {
                displayTeam(team);
            }
        }
    }

    private void displayTeam(Team team) {
        logger.info("Team ID: " + team.getId());
        logger.info("Name: " + team.getName());
        logger.info("Ranking: " + team.getRanking());
        logger.info("--------------------");
    }

    private void assignTeamToTournament() {
        logger.info("Enter team ID: ");
        long teamId = scanner.nextLong();

        Team team = teamController.getTeamById(teamId);
        if (team != null) {
            logger.info("Enter tournament ID: ");
            long tournamentId = scanner.nextLong();

            boolean success = teamController.assignTeamToTournament(teamId, tournamentId);
            if (success) {
                logger.info("Team assigned to tournament successfully.");
            } else {
                logger.info("Failed to assign team to tournament. Please check if the tournament exists.");
            }
        } else {
            logger.info("Team not found.");
        }
    }

    private void assignPrizeToTeam() {
        logger.info("Enter team id: ");
        long teamId = scanner.nextLong();

        Team team = teamController.getTeamById(teamId);

        if (team != null) {
            logger.info("Enter placement (1, 2, or 3): ");
            int placement = scanner.nextInt();

            Prize prize = null;
            switch (placement) {
                case 1:
                    prize = (Prize) context.getBean("1st");
                    break;
                case 2:
                    prize = (Prize) context.getBean("2nd");
                    break;
                case 3:
                    prize = (Prize) context.getBean("3rd");
                    break;
                default:
                    logger.info("Invalid placement. Please enter 1, 2, or 3.");
                    return;
            }

            if (prize != null) {
                boolean success = teamController.assignPrizeToTeam(teamId, prize);
                if (success) {
                    logger.info("Prize of {} assigned to team successfully.", prize.getAmount());
                } else {
                    logger.info("Failed to assign prize to team.");
                }
            }
        } else {
            logger.info("Team not found.");
        }
    }

    private void viewTeamsWithPrizes() {
        List<Team> teams = teamController.getTeamHavingPrize();
        if (teams.isEmpty()) {
            logger.info("No teams with prizes found.");
        } else {
            logger.info("Teams with prizes:");
            for (Team team : teams) {
                logger.info("Team Information:");
                displayTeam(team);
                logger.info("Prize Information:");
                logger.info("  Amount: ${}", team.getPrize().getAmount());
                logger.info("  Rank: {}", team.getPrize().getRank());
                logger.info("  Attribution Date: {}", team.getPrize().getAttributionDate());
                logger.info("----------------------------------------");
            }
        }
    }

    private void wiewPrizelessTeams() {
        List<Team> teams = teamController.getTeamNotHavingPrize();
        if (teams.isEmpty()) {
            logger.info("No teams without prizes found.");
        } else {
            logger.info("Teams without prizes:");
            for (Team team : teams) {
                displayTeam(team);
                logger.info("----------------------------------------");
            }
        }
    }
}
