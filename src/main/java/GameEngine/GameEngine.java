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

    public GameEngineController getD_gc() {
        return d_gc;
    }

    private static GameEngineController d_gc;

    public GameEngine(){
        d_gc = new GameEngineController();
    }

    public void setD_phase(Phases p_phase){
        d_phase = p_phase;
    }

    public Phases getD_phase(){
        return d_phase;
    }

    public static Phases getPhase(){
        return d_phase;
    }

    public void start(){
        setD_phase(new MapEditor(this));
        d_gc.nextUserInput();
    }

}