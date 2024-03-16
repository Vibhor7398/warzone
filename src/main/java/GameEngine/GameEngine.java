package GameEngine;
/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
import Controller.GameEngineController;
import Models.Command;
import Phases.MapEditor.MapEditor;
import Phases.Phases;
import Services.CommandValidator;
import Services.InvalidCommandException;

import java.util.Scanner;

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

//    public void nextUserInput() {
//        CommandValidator l_cs = new CommandValidator();
//        try{
//            Scanner l_sc = new Scanner(System.in);
//            System.out.println("Enter your command");
//            String l_command = l_sc.nextLine();
//            Command[] l_val= l_cs.validateCommand(l_command);
//            d_phase.execute(l_val);
//        } catch (InvalidCommandException e) {
//            System.out.println(e.getMessage());
//            nextUserInput();
//        }
//    }

    public void start(){
        setD_phase(new MapEditor(this));
        d_gc.nextUserInput();
    }

}