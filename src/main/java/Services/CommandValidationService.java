package Services;

import Constants.AppConstants;

import java.io.File;

public class CommandValidationService {
    public boolean validateCommand(String p_cmd){
        if(p_cmd == null){
            return false;
        }
        String[] l_cmdArr = p_cmd.trim().split(" ");

        String l_baseCmd = getBaseCommand(p_cmd);
        switch (l_baseCmd) {
            case "loadmap":
                return validateLoadMapCommand(l_cmdArr);

            case "showmap":
                return l_cmdArr.length==1;

            case "savemap":
                return validateSaveMapCommand(l_cmdArr);

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
                return l_cmdArr.length==1;

            case "deploy":
                return validateDeployCommand(l_cmdArr);

            default:
                System.out.println("Please try again");
                return false;
        }
    }

    public String getBaseCommand(String p_cmd){
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
            return p_cmd[1].trim().equals("-add") && l_ms.isInteger(p_cmd[2]) && l_ms.isInteger(p_cmd[3]);
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

    private boolean validateDeployCommand(String[] p_cmd){
        if(p_cmd.length != 3){
            return false;
        }

        if (p_cmd[0] == null || p_cmd[1] == null || p_cmd[2] == null) {
            System.out.println("Parameter mismatch. Try again.");
            return false;
        }

        MathService l_ms = new MathService();
        return l_ms.isInteger(p_cmd[1]) && l_ms.isInteger(p_cmd[2]);
    }
}
