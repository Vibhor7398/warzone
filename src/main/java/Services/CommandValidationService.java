package Services;

import Constants.AppConstants;
import java.io.File;
import java.util.ArrayList;

public class CommandValidationService {
    private ArrayList<String> d_mapEditorCommands;
    private ArrayList<String> d_gameEditorCommands;

    public static void setD_hasGameStarted(boolean d_hasGameStarted) {
        CommandValidationService.d_hasGameStarted = d_hasGameStarted;
    }

    private static boolean d_hasGameStarted;

    public CommandValidationService(){
//        d_hasGameStarted = p_hasGameStarted;
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
    public CommandValidationService(boolean p_hasGameStarted){

    }
    public boolean validateCommand(String p_cmd){
        if(p_cmd == null){
            return false;
        }
        String[] l_cmdArr = p_cmd.trim().split(" ");

        String l_baseCmd = getBaseCommand(p_cmd);

        if(!d_hasGameStarted && !d_mapEditorCommands.contains(l_baseCmd)){
            return false;
        }
        else if(d_hasGameStarted && !d_gameEditorCommands.contains(l_baseCmd)){
            return false;
        }

        switch (l_baseCmd) {
            case "loadmap":
                return validateLoadMapCommand(l_cmdArr);

            case "editmap", "savemap":
                return validateSaveMapCommand(l_cmdArr);

            case "showmap":
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

            case "assigncountries":
                if(l_cmdArr.length==1){
                    d_hasGameStarted = true;
                    return true;
                }
                return false;

            case "deploy":
                return validateDeployCommand(l_cmdArr);

            default:
                System.out.println("Please try again");
                return false;
        }
    }

    public static String getBaseCommand(String p_cmd){
        return p_cmd.trim().split(" ")[0];
    }

    private boolean validateLoadMapCommand(String[] p_cmd) {
        if(p_cmd.length != 2){
            return false;
        }
        String filename = p_cmd[1];
        try{
            File f = new File(AppConstants.MapsPath + filename);
            return f.exists() && !f.isDirectory();
        }
        catch (Exception ex){
            System.out.println("File Not Found!");
            return false;
        }
    }

    private boolean validateSaveMapCommand(String[] p_cmd){
        if(p_cmd.length != 2){
            return false;
        }
        String filename = p_cmd[1];
        if(filename.endsWith(".map")){
            return true;
        }
        System.out.println("Incorrect filename format");
        return false;
    }

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
            MathService l_ms = new MathService();
            boolean l_isValid = p_cmd[1].trim().equals("-remove") && !p_cmd[2].trim().isEmpty();
            return validateCommand(l_isValid);
        }
    }

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
            MathService l_ms = new MathService();
            boolean l_isValid = p_cmd[1].trim().equals("-add") && !p_cmd[2].trim().isEmpty() && !p_cmd[3].trim().isEmpty();
            return validateCommand(l_isValid);
        }
        else {
            MathService l_ms = new MathService();
            boolean l_isValid = p_cmd[1].trim().equals("-remove") && !p_cmd[2].trim().isEmpty();
            return validateCommand(l_isValid);
        }
    }

    private boolean validateEditNeighborCommand(String[] p_cmd){
        if(p_cmd.length != 4){
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }

        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null || p_cmd[3] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }

        MathService l_ms = new MathService();
        boolean l_isValid = (p_cmd[1].trim().equals("-add") || p_cmd[1].trim().equals("-remove")) && !p_cmd[2].trim().isEmpty() && !p_cmd[3].trim().isEmpty();
        return validateCommand(l_isValid);
    }

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

    private boolean validateDeployCommand(String[] p_cmd){
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

    private boolean validateCommand(boolean p_isValid){
        if(p_isValid){
            return true;
        }
        System.out.println("Invalid command. Try again.");
        return false;
    }
}
