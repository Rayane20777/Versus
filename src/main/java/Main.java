import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import versus.controller.GameController;
import versus.model.Game;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the GameController bean
        GameController gameController = context.getBean(GameController.class);

        // Example: Save a new game
        Game newGame = new Game("Test Game", 12, 30);
        gameController.createGame(newGame);

    }
}
