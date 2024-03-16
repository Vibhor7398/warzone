package GameEngine;
/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
import Models.Command;
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

    public void setD_phase(Phases p_phase){
        d_phase = p_phase;
    }

    public Phases getD_phase(){
        return d_phase;
    }

    public void nextUserInput() {
        CommandValidator l_cs = new CommandValidator();
        try{
            Scanner l_sc = new Scanner(System.in);
            System.out.println("Enter your command");
            String l_command = l_sc.nextLine();
            Command val= l_cs.validateCommand(l_command);
            // Set the phase here now
            System.out.println(val.toString());
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            nextUserInput();
        }
    }

    public void start(){
        nextUserInput();
    }

}