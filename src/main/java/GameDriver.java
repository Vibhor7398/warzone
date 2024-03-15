import GameEngine.GameEngine;

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
