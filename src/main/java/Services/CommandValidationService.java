package Services;

import Constants.AppConstants;

import java.io.File;

public class CommandValidationService {
    public boolean validateCommand(String p_cmd){
        if(p_cmd == null){
            return false;
        }
        String[] l_cmdArr = p_cmd.trim().split("\\ ");

        String l_baseCmd = getBaseCommand(p_cmd);
        switch (l_baseCmd.toLowerCase()) {
            case "loadmap":
                System.out.println("loadmap");
                return validateLoadMapCommand(l_cmdArr);

            case "showmap":
                System.out.println("showmap");
                return true;

            case "savemap":
                System.out.println("savemap");
                return validateSaveMapCommand(l_cmdArr);

            case "editcontinent":
                System.out.println("editcontinent");
                return validateEditContinentCommand(l_cmdArr);

            case "editcountry":
                System.out.println("editcountry");
                return validateEditCountryCommand(l_cmdArr);

            case "editneighbor":
                System.out.println("editneighbor");
                return validateEditNeighborCommand(l_cmdArr);

            case "validatemap":
                System.out.println("validatemap");
                return true;

            case "gameplayer":
                System.out.println("gameplayer");
                return validateGamePlayerCommand(l_cmdArr);

            case "assigncountries":
                System.out.println("assigncountries");
                return true;

            default:
                System.out.println("Please try again");
                return false;
        }
    }

    private String getBaseCommand(String p_cmd){
        return p_cmd.trim().split("\\ ")[0];
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
            return p_cmd[1].trim().equals("-add") && l_ms.isInteger(p_cmd[2]) && !p_cmd[3].trim().isEmpty();
        }
        else {
            MathService l_ms = new MathService();
            return p_cmd[1].trim().equals("-remove") && l_ms.isInteger(p_cmd[2]);
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
            return p_cmd[1].trim().equals("-add") && l_ms.isInteger(p_cmd[2]) && l_ms.isInteger(p_cmd[3]);
        }
        else {
            MathService l_ms = new MathService();
            return p_cmd[1].trim().equals("-remove") && l_ms.isInteger(p_cmd[2]);
        }
    }

    private boolean validateEditNeighborCommand(String[] p_cmd){
        if(p_cmd.length != 4){
            return false;
        }

        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null || p_cmd[3] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }

        MathService l_ms = new MathService();
        return (p_cmd[1].trim().equals("-add") || p_cmd[1].trim().equals("-remove")) && l_ms.isInteger(p_cmd[2]) && l_ms.isInteger(p_cmd[3]);
    }

    private boolean validateGamePlayerCommand(String[] p_cmd){
        if(p_cmd.length != 3){
            return false;
        }

        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }

        return (p_cmd[1].trim().equals("-add") || p_cmd[1].trim().equals("-remove")) && !p_cmd[2].trim().isEmpty();
    }

}
