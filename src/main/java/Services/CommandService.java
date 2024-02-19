package Services;

import Controller.GameEngineController;
import java.util.Scanner;

/**
 * This class represents a service for handling commands from the user.
 * It provides methods for getting and executing commands within a game environment.
 */
public class CommandService {
    CommandValidationService d_cvs = new CommandValidationService();
    GameEngineController d_gc;

    /**
     * Constructs a CommandService object.
     * Initializes the GameEngineController instance.
     */
    public CommandService(){
        d_gc = new GameEngineController();
    }

    /**
     * Prompts the user to enter a command and returns it.
     * If the entered command is invalid, prompts the user again until a valid command is entered.
     * @return The validated command entered by the user.
     */
    public String getNextCommand(){
        System.out.println("Please enter your command");
        Scanner l_sc = new Scanner(System.in);
        String l_command = l_sc.nextLine();
        if(!d_cvs.validateCommand(l_command)){
            System.out.print("Invalid Command! ");
            return getNextCommand();
        }
        return l_command;
    }

    /**
     * Starts the command service by continuously prompting the user for commands and executing them.
     */
    public void start(){
        while (true){
            String l_command = getNextCommand();
            executeCommand(l_command);
        }
    }

    /**
     * Executes the given command by passing it to the GameEngineController.
     * @param p_cmd The command to be executed.
     */
    public void executeCommand(String p_cmd){
        d_gc.executeCommand(p_cmd);
    }
}
