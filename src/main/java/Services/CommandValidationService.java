package Services;

import Constants.AppConstants;
import java.io.File;
import java.util.ArrayList;

/**
 * This class provides validation services for commands in a game environment.
 * It ensures that commands entered by the user are valid and appropriate for the current game state.
 */
public class CommandValidationService {
    private ArrayList<String> d_mapEditorCommands;
    private ArrayList<String> d_gameEditorCommands;
    private static boolean d_HasGameStarted;

    /**
     * Sets whether the game has started.
     * @param p_hasGameStarted A boolean indicating whether the game has started.
     */
    public static void setD_hasGameStarted(boolean p_hasGameStarted) {
        CommandValidationService.d_HasGameStarted = p_hasGameStarted;
    }

    /**
     * Constructs a CommandValidationService object and initializes command lists.
     */
    public CommandValidationService(){
        d_mapEditorCommands = new ArrayList<>();
        d_gameEditorCommands = new ArrayList<>();
        d_mapEditorCommands.add("loadmap");
        d_mapEditorCommands.add("showmap");
        d_mapEditorCommands.add("savemap");
        d_mapEditorCommands.add("editmap");
        d_mapEditorCommands.add("editcontinent");
        d_mapEditorCommands.add("editcountry");
        d_mapEditorCommands.add("editneighbor");
        d_mapEditorCommands.add("validatemap");
        d_mapEditorCommands.add("gameplayer");
        d_mapEditorCommands.add("assigncountries");
        d_gameEditorCommands.add("showmap");
        d_gameEditorCommands.add("deploy");
        d_gameEditorCommands.add("validatemap");
    }

    /**
     * Validates the given command.
     * @param p_cmd The command to be validated.
     * @return A boolean indicating whether the command is valid.
     */
    public boolean validateCommand(String p_cmd){
        if(p_cmd == null){
            return false;
        }
        String[] l_cmdArr = p_cmd.trim().split("\\ ");
        String l_baseCmd = getBaseCommand(p_cmd);
        if(!d_HasGameStarted && !d_mapEditorCommands.contains(l_baseCmd)){
            return false;
        }
        else if(d_HasGameStarted && !d_gameEditorCommands.contains(l_baseCmd)){
            return false;
        }
        switch (l_baseCmd) {
            case "loadmap":
                return validateLoadMapCommand(l_cmdArr);
            case "editmap", "savemap":
                return validateSaveMapCommand(l_cmdArr);
            case "showmap", "assigncountries":
                return l_cmdArr.length==1;
            case "editcontinent":
                return validateEditContinentCommand(l_cmdArr);
            case "editcountry":
                return validateEditCountryCommand(l_cmdArr);
            case "editneighbor":
                return validateEditNeighborCommand(l_cmdArr);
            case "validatemap":
                return l_cmdArr.length==1;
            case "gameplayer":
                return validateGamePlayerCommand(l_cmdArr);
            case "deploy":
                return validateDeployCommand(l_cmdArr);
            default:
                System.out.println("Please try again");
                return false;
        }
    }

    /**
     * Retrieves the base command from a full command string.
     * @param p_cmd The full command string.
     * @return The base command.
     */
    public static String getBaseCommand(String p_cmd){
        return p_cmd.trim().split("\\ ")[0];
    }

    /**
     * Validates the "loadmap" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateLoadMapCommand(String[] p_cmd) {
        if(p_cmd.length != 2){
            return false;
        }
        String l_filename = p_cmd[1];
        try{
            File l_f = new File(AppConstants.MapsPath + l_filename);
            return l_f.exists() && !l_f.isDirectory();
        }
        catch (Exception l_ex){
            System.out.println("File Not Found!");
            return false;
        }
    }

    /**
     * Validates the "savemap" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateSaveMapCommand(String[] p_cmd){
        if(p_cmd.length != 2){
            return false;
        }
        String l_filename = p_cmd[1];
        if(l_filename.endsWith(".map")){
            return true;
        }
        System.out.println("Incorrect filename format");
        return false;
    }

    /**
     * Validates the "editcontinent" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateEditContinentCommand(String[] p_cmd){
        if(!(p_cmd.length == 3 || p_cmd.length == 4)){
            return false;
        }
        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }
        if(p_cmd.length == 4){
            if (p_cmd[3] == null) {
                System.out.println("Parameter mismatch. Try again.");
                return false;
            }
            MathService l_ms = new MathService();
            boolean l_isValid = p_cmd[1].trim().equals("-add") && l_ms.isInteger(p_cmd[3]) && !p_cmd[2].trim().isEmpty();
            return validateCommand(l_isValid);
        }
        else {
            boolean l_isValid = p_cmd[1].trim().equals("-remove") && !p_cmd[2].trim().isEmpty();
            return validateCommand(l_isValid);
        }
    }

    /**
     * Validates the "editcountry" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateEditCountryCommand(String[] p_cmd){
        if(!(p_cmd.length == 3 || p_cmd.length == 4)){
            return false;
        }
        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }
        if(p_cmd.length == 4){
            if (p_cmd[3] == null) {
                System.out.println("Parameter mismatch. Try again.");
                return false;
            }
            boolean l_isValid = p_cmd[1].trim().equals("-add") && !p_cmd[2].trim().isEmpty() && !p_cmd[3].trim().isEmpty();
            return validateCommand(l_isValid);
        }
        else {
            boolean l_isValid = p_cmd[1].trim().equals("-remove") && !p_cmd[2].trim().isEmpty();
            return validateCommand(l_isValid);
        }
    }

    /**
     * Validates the "editneighbor" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateEditNeighborCommand(String[] p_cmd){
        if(p_cmd.length != 4){
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }
        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null || p_cmd[3] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }
        boolean l_isValid = (p_cmd[1].trim().equals("-add") || p_cmd[1].trim().equals("-remove")) && !p_cmd[2].trim().isEmpty() && !p_cmd[3].trim().isEmpty();
        return validateCommand(l_isValid);
    }

    /**
     * Validates the "gameplayer" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateGamePlayerCommand(String[] p_cmd){
        if(p_cmd.length != 3){
            return false;
        }
        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }
        boolean l_isValid = (p_cmd[1].trim().equals("-add") || p_cmd[1].trim().equals("-remove")) && !p_cmd[2].trim().isEmpty();
        return validateCommand(l_isValid);
    }

    /**
     * Validates the "deploy" command.
     * @param p_cmd The command and its parameters.
     * @return true if the command is valid, false otherwise.
     */
    public boolean validateDeployCommand(String[] p_cmd){
        if(p_cmd.length != 3){
            return false;
        }
        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }
        MathService l_ms = new MathService();
        boolean l_isValid = !p_cmd[1].trim().isEmpty() && l_ms.isInteger(p_cmd[2]);
        return validateCommand(l_isValid);
    }

    /**
     * Validates the result of a command validation process.
     * @param p_isValid A boolean indicating whether the command is considered valid.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateCommand(boolean p_isValid){
        if(p_isValid){
            return true;
        }
        System.out.println("Invalid command. Try again.");
        return false;
    }
}
