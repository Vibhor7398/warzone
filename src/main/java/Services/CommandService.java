package Services;

import java.util.Scanner;

public class CommandService {
    public String d_command;

//     Maps map = new Maps();
//     MapView mapView=new MapView();

    CommandValidationService l_cvs = new CommandValidationService();
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

    public void start(){
        while (true){
            String l_command = getNextCommand();
            executeCommand(l_command);
        }
    }

    public void executeCommand(String p_command){
        String l_baseCmd = CommandValidationService.getBaseCommand(p_command);
        String[] l_cmdArr = p_command.trim().split("\\ ");
        switch (l_baseCmd){
            case "loadmap":
                executeLoadMap(l_cmdArr[1]);
                break;

            case "showmap":
                executeShowMap();
                break;

            case "savemap":
                executeSaveMap();
                break;

            case "editcontinent":
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddContinent(l_cmdArr[2], Integer.parseInt(l_cmdArr[3]));
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveContinent(l_cmdArr[2]);
                break;

            case "editcountry":
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddCountry(l_cmdArr[2], l_cmdArr[3]);
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveCountry(l_cmdArr[2]);
                break;

            case "editneighbor":
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddNeighbor(l_cmdArr[2], l_cmdArr[3]);
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveNeighbor(l_cmdArr[2], l_cmdArr[3]);
                break;

            case "validatemap":
                executeValidateMap();
                break;

            case "gameplayer":
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddGamePlayer(l_cmdArr[2]);
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveGamePlayer(l_cmdArr[2]);
                break;

            case "assigncountries":
                executeAssignCountries();
                break;

            case "deploy":
                executeDeploy(l_cmdArr[1], Integer.parseInt(l_cmdArr[2]));
                break;

            case "exit":
        }
    }

    private void executeLoadMap(String p_filename){
//         map.loadMap(p_filename);
        System.out.println("executeLoadMap");
    }

    private void executeShowMap(){
//        mapView.showMap(map.getContinents(),map.getCountries());
        System.out.println("executeShowMap");
    }

    private void executeSaveMap(){
//        mapView.SaveMap();
        System.out.println("executeSaveMap");
    }

    private void executeAddContinent(String p_continentID, int p_continentvalue){
//        AddContinent
        System.out.println("executeAddContinent");
    }

    private void executeRemoveContinent(String p_continentID){
//        RemoveContinent
        System.out.println("executeRemoveContinent");
    }

    private void executeAddCountry(String p_countryID, String p_continentID){
//        AddCountry
        System.out.println("executeAddCountry");
    }

    private void executeRemoveCountry(String p_countryID){
//        RemoveCountry
        System.out.println("executeRemoveCountry");
    }

    private void executeAddNeighbor(String p_countryID, String p_neighborcountryID){
//        AddNeighbor
        System.out.println("executeAddNeighbor");
    }

    private void executeRemoveNeighbor(String p_countryID, String p_neighborcountryID){
//        RemoveNeighbor
        System.out.println("executeRemoveNeighbor");
    }

    private void executeValidateMap(){
//        Validate Map
        System.out.println("executeValidateMap");
    }

    private void executeAddGamePlayer(String p_gameplayer){
//        Add Game Player
        System.out.println("executeAddGamePlayer");
    }

    private void executeRemoveGamePlayer(String p_gameplayer){
//        Remove Game Player
        System.out.println("executeRemoveGamePlayer");
    }

    private void executeAssignCountries(){
//        Assign Countries
        System.out.println("executeAssignCountries");
    }

    private void executeDeploy(String p_countryID, int num){
//        Deploy
        System.out.println("executeDeploy");
    }
}
