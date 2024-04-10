/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
import Controller.GameEngineController;
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
            System.out.println("---------Welcome to WARZONE-------");
            GameEngineController.d_Log.notify("Game has started!");
            GameEngine l_ge = new GameEngine();
            try{
                l_ge.start();
            }
            catch (StackOverflowError err){
                System.out.println("Game will run infinitely!");
            }
    }
}
