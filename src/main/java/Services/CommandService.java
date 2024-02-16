package Services;

import java.util.Scanner;

public class CommandService {
    public String d_command;
    public boolean d_isValid;

//     Maps map = new Maps();
//     MapView mapView=new MapView();


    CommandValidationService l_cvs = new CommandValidationService();
    public void getNextCommand(){
        System.out.println("Please enter your command");
        Scanner l_sc = new Scanner(System.in);
        d_command = l_sc.nextLine();
        d_isValid = l_cvs.validateCommand(d_command);
    }

    public void start(){
        while (true){
            getNextCommand();
            System.out.println(d_isValid);
            if(d_isValid){
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
                        System.out.println("validatemap");
                        break;

                    case "gameplayer":
                        System.out.println("gameplayer");
                        break;

                    case "assigncountries":
                        System.out.println("assigncountries");
                        break;
                }
            }
            else {
                System.out.println("Invalid Command");
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
}
