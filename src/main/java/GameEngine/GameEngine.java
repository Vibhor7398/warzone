/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package GameEngine;

import Controller.GameEngineController;
import Phases.MapEditor.MapEditor;
import Phases.Phases;

/**
 * This class represents the game engine for the application.
 * It handles the main entry point of the game and initializes the game environment.
 */
public class GameEngine {
    private static Phases d_phase;
    /**
     * Gets the GameEngineController associated with this GameEngine.
     *
     * @return The GameEngineController.
     */
    public GameEngineController getD_gc() {
        return d_gc;
    }

    private static GameEngineController d_gc;

    /**
     * Constructs a GameEngine object and initializes the GameEngineController.
     */
    public GameEngine(){
        d_gc = new GameEngineController();
    }

    /**
     * Sets the current phase of the game.
     *
     * @param p_phase The phase to set.
     */
    public void setD_phase(Phases p_phase){
        d_phase = p_phase;
    }

    /**
     * Gets the current phase of the game.
     *
     * @return The current phase of the game.
     */
    public Phases getD_phase(){
        return d_phase;
    }


    /**
     * Gets the current phase of the game.
     *
     * @return The current phase of the game.
     */
    public static Phases getPhase(){
        return d_phase;
    }

    /**
     * Starts the game by setting the initial phase to MapEditor and triggering user input.
     */
    public void start(){
        setD_phase(new MapEditor(this));
        d_gc.nextUserInput();
    }

}