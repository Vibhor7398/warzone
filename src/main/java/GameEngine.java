/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
import Services.CommandService;
import Services.CommandValidationService;

/**
 * This class represents the game engine for the application.
 * It handles the main entry point of the game and initializes the game environment.
 */
public class GameEngine {

    /**
     * The main method that starts the game.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        start();
    }

    /**
     * Starts the game engine.
     * It sets up the initial game environment, prompts the user to load or edit a map, and starts the command service.
     */
    private static void start(){
        CommandValidationService.setD_hasGameStarted(false);
        CommandService l_cs = new CommandService();
        String l_command =  l_cs.getNextCommand();
        String l_cmd = CommandValidationService.getBaseCommand(l_command);
        while(!(l_cmd.equals("loadmap") || l_cmd.equals("editmap"))){
            System.out.println("Invalid Command. Need to load map first.");
            l_command = l_cs.getNextCommand();
            l_cmd = CommandValidationService.getBaseCommand(l_command);
        }
        l_cs.executeCommand(l_command);
        l_cs.start();
    }
}