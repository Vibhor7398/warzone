/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
import GameEngine.GameEngine;

/**
 * The main class that serves as the entry point for the game.
 * It creates an instance of the GameEngine and starts the game.
 */
public class GameDriver {
    /**
     * The main method that starts the game.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        GameEngine l_ge = new GameEngine();
        l_ge.start();
    }
}
