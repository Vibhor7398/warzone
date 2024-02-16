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
        d_command = l_sc.nextLine();
        if(!l_cvs.validateCommand(d_command)){
            return getNextCommand();
        }
        return d_command;
    }

    public void start(){
        while (true){
            getNextCommand();
            String l_baseCmd = l_cvs.getBaseCommand(d_command);
            String[] l_cmdArr = d_command.trim().split("\\ ");

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
                        executeAddContinent(Integer.parseInt(l_cmdArr[2]), Integer.parseInt(l_cmdArr[3]));
                    else if(l_cmdArr[1].trim().equals("-remove"))
                        executeRemoveContinent(Integer.parseInt(l_cmdArr[2]));
                    break;

                case "editcountry":
                    if(l_cmdArr[1].trim().equals("-add"))
                        executeAddCountry(Integer.parseInt(l_cmdArr[2]), Integer.parseInt(l_cmdArr[3]));
                    else if(l_cmdArr[1].trim().equals("-remove"))
                        executeRemoveCountry(Integer.parseInt(l_cmdArr[2]));
                    break;

                case "editneighbor":
                    if(l_cmdArr[1].trim().equals("-add"))
                        executeAddNeighbor(Integer.parseInt(l_cmdArr[2]), Integer.parseInt(l_cmdArr[3]));
                    else if(l_cmdArr[1].trim().equals("-remove"))
                        executeRemoveNeighbor(Integer.parseInt(l_cmdArr[2]), Integer.parseInt(l_cmdArr[3]));
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
                    executeDeploy(Integer.parseInt(l_cmdArr[1]), Integer.parseInt(l_cmdArr[2]));
                    break;
            }
        }
    }

    private void executeLoadMap(String p_filename){
//         map.loadMap(p_filename);
    }

    private void executeShowMap(){
//        mapView.showMap(map.getContinents(),map.getCountries());
    }

    private void executeSaveMap(){
//        mapView.SaveMap();
    }

    private void executeAddContinent(int p_continentID, int p_continentvalue){
//        AddContinent
    }

    private void executeRemoveContinent(int p_continentID){
//        RemoveContinent
    }

    private void executeAddCountry(int p_countryID, int p_continentID){
//        AddCountry
    }

    private void executeRemoveCountry(int p_countryID){
//        RemoveCountry
    }

    private void executeAddNeighbor(int p_countryID, int p_neighborcountryID){
//        AddNeighbor
    }

    private void executeRemoveNeighbor(int p_countryID, int p_neighborcountryID){
//        RemoveNeighbor
    }

    private void executeValidateMap(){
//        Validate Map
    }

    private void executeAddGamePlayer(String p_gameplayer){
//        Add Game Player
    }

    private void executeRemoveGamePlayer(String p_gameplayer){
//        Remove Game Player
    }

    private void executeAssignCountries(){
//        Assign Countries
    }

    private void executeDeploy(int p_countryID, int num){
//        Deploy
    }
}
