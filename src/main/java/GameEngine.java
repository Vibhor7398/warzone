/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
import Constants.PhasesEnum;
import Controller.MapsController;
import Controller.GameEngineController;
import Phases.Phases;
import Phases.MapEditor.MapEditor;
import Services.CommandService;

/**
 * This class represents the game engine for the application.
 * It handles the main entry point of the game and initializes the game environment.
 */
public class GameEngine {
    private static Phases d_phase;

    public static void setD_phase(Phases p_phase){
        d_phase = p_phase;
    }

    public static Phases getD_phase(){
        return d_phase;
    }


    /**
     * Starts the game engine.
     * It sets up the initial game environment, prompts the user to load or edit a map, and starts the command service.
     */
//    private static void start(){
//        CommandValidationService.setD_hasGameStarted(false);
//        CommandService l_cs = new CommandService();
//        String l_command =  l_cs.getNextCommand();
//        String l_cmd = CommandValidationService.getBaseCommand(l_command);
//        while(!(l_cmd.equals("loadmap") || l_cmd.equals("editmap"))){
//            System.out.println("Invalid Command. Need to load map first.");
//            l_command = l_cs.getNextCommand();
//            l_cmd = CommandValidationService.getBaseCommand(l_command);
//        }
//        l_cs.executeCommand(l_command);
//        l_cs.start();
//    }
    public void start(){
        setD_phase(new MapEditor());
        MapsController l_mapsController = new MapsController();
        GameEngineController l_gc = new GameEngineController(l_mapsController);
        while(true){
            Phases l_phase = getD_phase();
            CommandService l_cs = new CommandService();
            String l_command =  l_cs.getNextCommand(); //TODO: return command object

        }
    }

}