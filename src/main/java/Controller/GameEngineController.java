package Controller;

import Constants.AppConstants;
import Models.Country;
import Models.Player;
import Services.CommandValidationService;
import Services.Reinforcement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameEngineController {
    private static ArrayList<Player> d_players;
    public static MapsController getD_map() {
        return d_map;
    }

    public static void setD_map(MapsController d_map) {
        GameEngineController.d_map = d_map;
    }

    private static MapsController d_map;

    public GameEngineController(){
        d_map = new MapsController();
        d_players = new ArrayList<>();
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
                executeSaveMap(l_cmdArr[1]);
                break;

            case "editmap":
                executeEditMap(l_cmdArr[1]);
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
                executeDeploy();
                break;
        }
    }

    private void executeLoadMap(String p_filename){
        try {
            d_map.loadMap(AppConstants.MapsPath+p_filename);
        } catch (IOException e) {
            System.out.print("Check input path! ");
        }
        System.out.println("executeLoadMap");
    }

    private void executeShowMap(){
        d_map.showMap();
        System.out.println("executeShowMap");
    }

    private void executeSaveMap(String p_filename){
        d_map.validateMap();
        boolean l_isValid = d_map.isMapValid();
        if(!l_isValid){
            System.out.println("Map is invalid!");
            return;
        }
        File file = new File(AppConstants.MapsPath+p_filename);
        try {
            d_map.saveMap(file);
        } catch (IOException e) {
            System.out.println("Map could not be saved!");
        }
        System.out.println("executeSaveMap");
    }

    private void executeEditMap(String p_filename){
        File file = new File(AppConstants.MapsPath+p_filename);
        try {
            d_map.editMap(file);
        } catch (IOException e) {
            System.out.println("Map could not be created");
        }
        System.out.println("executeEditMap");
    }

    private void executeAddContinent(String p_continentID, int p_continentvalue){
        d_map.addContinent(p_continentID,p_continentvalue);
        System.out.println("executeAddContinent");
    }

    private void executeRemoveContinent(String p_continentID){
        d_map.removeContinent(p_continentID);
        System.out.println("executeRemoveContinent");
    }

    private void executeAddCountry(String p_countryID, String p_continentID){
        d_map.addCountry(p_countryID,p_continentID);
        System.out.println("executeAddCountry");
    }

    private void executeRemoveCountry(String p_countryID){
        d_map.removeCountry(p_countryID);
        System.out.println("executeRemoveCountry");
    }

    private void executeAddNeighbor(String p_countryID, String p_neighborcountryID){
        d_map.editNeighbors("add", p_countryID, p_neighborcountryID);
        System.out.println("executeAddNeighbor");
    }

    private void executeRemoveNeighbor(String p_countryID, String p_neighborcountryID){
        d_map.editNeighbors("remove", p_countryID, p_neighborcountryID);
        System.out.println("executeRemoveNeighbor");
    }

    private void executeValidateMap(){
        d_map.validateMap();
        boolean l_isValid = d_map.isMapValid();
        if (l_isValid) {
            System.out.println("Status of map: Valid");
        }
        else {
            System.out.println("Status of map: Invalid");
        }
    }

    private void executeAddGamePlayer(String p_gameplayer){
        int l_playerIndex = doesPlayerExists(p_gameplayer);
        if (l_playerIndex != -1){
            System.out.println("Player name already exists!");
        }
        else{
            d_players.add(new Player(p_gameplayer));
        }
        System.out.println("executeAddGamePlayer");
    }

    private int doesPlayerExists(String p_gameplayer){
        for(int i = 0 ; i < d_players.size() ; i++){
            if(d_players.get(i).getName().equals(p_gameplayer)){
                return i;
            }
        }
        return -1;
    }

    private void executeRemoveGamePlayer(String p_gameplayer){
        int l_playerIndex = doesPlayerExists(p_gameplayer);
        if(l_playerIndex != -1){
            d_players.remove(l_playerIndex);
        }
        else{
            System.out.println("Player does not exist!");
        }
        System.out.println("executeRemoveGamePlayer");
    }

    private void executeAssignCountries(){
        if(d_players.size() < 2){
            System.out.println("Cannot play with less than 2 players");
            CommandValidationService.setD_hasGameStarted(false);
            return;
        }
        HashMap<String, Country> l_listOfCountries = d_map.getD_countries();
        int l_NumPlayers = d_players.size();
        int l_playerIndex = 0;

        for(Country l_country : l_listOfCountries.values()){
            System.out.println("Assigning " + l_country.getName() + " to " + d_players.get(l_playerIndex).getName());
            d_players.get(l_playerIndex++).addCountryToCountriesOwned(l_country);
            if(l_playerIndex == l_NumPlayers){
                l_playerIndex = 0;
            }
        }
        System.out.println("executeAssignCountries");
        CommandValidationService.setD_hasGameStarted(true);
        Reinforcement.assignReinforcements(d_players);
        executeDeploy();
    }

    private void executeDeploy(){
        System.out.println("executeDeploy");
        while(Player.getD_reinforcementsCompleted() != d_players.size()){
            for(Player l_player : d_players){
                l_player.issue_order();
            }
        }
        Player.setD_reinforcementsCompleted(0);
        while(Player.getD_reinforcementsCompleted() != d_players.size()){
            for(Player l_player : d_players){
                l_player.issue_order();
            }
        }
    }
}