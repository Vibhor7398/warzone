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
    private static ArrayList<Player> d_Players;
    private static MapsController d_Map;

    public GameEngineController(){
        d_Map = new MapsController();
        d_Players = new ArrayList<>();
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
            d_Map.loadMap(AppConstants.MapsPath + p_filename);
            d_Map.validateMap();
            boolean l_isValid = d_Map.isMapValid();
            if(!l_isValid){
                System.out.println("Map is invalid!");
            }
        } catch (IOException l_e) {
            System.out.println("Load map failed. Check for map file. " + l_e.getMessage());
        }
    }

    private void executeShowMap(){
        d_Map.showMap();
    }

    private void executeSaveMap(String p_filename){
        d_Map.validateMap();
        boolean l_isValid = d_Map.isMapValid();
        if(!l_isValid){
            System.out.println("Map is invalid!");
            return;
        }
        File l_file = new File(AppConstants.MapsPath + p_filename);
        try {
            d_Map.saveMap(l_file);
        } catch (IOException l_e) {
            System.out.println("Map could not be saved! " + l_e.getMessage());
        }
    }

    private void executeEditMap(String p_filename){
        File l_file = new File(AppConstants.MapsPath + p_filename);
        try {
            d_Map.editMap(l_file);
        } catch (IOException l_e) {
            System.out.println("Map could not be created. " + l_e.getMessage());
        }
    }

    private void executeAddContinent(String p_continentID, int p_continentValue){
        d_Map.addContinent(p_continentID,p_continentValue);
    }

    private void executeRemoveContinent(String p_continentID){
        d_Map.removeContinent(p_continentID);
    }

    private void executeAddCountry(String p_countryID, String p_continentID){
        d_Map.addCountry(p_countryID,p_continentID);
    }

    private void executeRemoveCountry(String p_countryID){
        d_Map.removeCountry(p_countryID);
    }

    private void executeAddNeighbor(String p_countryID, String p_neighborCountryID){
        d_Map.editNeighbors("add", p_countryID, p_neighborCountryID);
    }

    private void executeRemoveNeighbor(String p_countryID, String p_neighborCountryID){
        d_Map.editNeighbors("remove", p_countryID, p_neighborCountryID);
    }

    private void executeValidateMap(){
        d_Map.validateMap();
        boolean l_isValid = d_Map.isMapValid();
        if (l_isValid) {
            System.out.println("Status of map: Valid");
        }
        else {
            System.out.println("Status of map: Invalid");
        }
    }

    private void executeAddGamePlayer(String p_gamePlayer){
        int l_playerIndex = doesPlayerExists(p_gamePlayer);
        if (l_playerIndex != -1){
            System.out.println("Player name already exists!");
        }
        else{
            d_Players.add(new Player(p_gamePlayer));
        }
    }

    private int doesPlayerExists(String p_gamePlayer){
        for(int i = 0 ; i < d_Players.size() ; i++){
            if(d_Players.get(i).getName().equals(p_gamePlayer)){
                return i;
            }
        }
        return -1;
    }

    private void executeRemoveGamePlayer(String p_gamePlayer){
        int l_playerIndex = doesPlayerExists(p_gamePlayer);
        if(l_playerIndex != -1){
            d_Players.remove(l_playerIndex);
        }
        else{
            System.out.println("Player does not exist!");
        }
    }

    private void executeAssignCountries(){
        if(d_Players.size() < 2){
            System.out.println("Cannot play with less than 2 players");
            CommandValidationService.setD_hasGameStarted(false);
            return;
        }
        HashMap<String, Country> l_listOfCountries = d_Map.getD_countries();
        int l_NumPlayers = d_Players.size();
        int l_playerIndex = 0;
        for(Country l_country : l_listOfCountries.values()){
            System.out.println("Assigning " + l_country.getName() + " to " + d_Players.get(l_playerIndex).getName());
            d_Players.get(l_playerIndex++).addCountryToCountriesOwned(l_country);
            if(l_playerIndex == l_NumPlayers){
                l_playerIndex = 0;
            }
        }
        System.out.println();
        System.out.println("-----------Main Game Loop---------");
        CommandValidationService.setD_hasGameStarted(true);
        Reinforcement.assignReinforcements(d_Players);
        executeDeploy();
    }

    private void executeDeploy(){
        while(Player.getD_reinforcementsCompleted() != d_Players.size()){
            for(Player l_player : d_Players){
                l_player.issue_order();
            }
        }
        Player.setD_reinforcementsCompleted(0);
        while(Player.getD_reinforcementsCompleted() != d_Players.size()){
            for(Player l_player : d_Players){
                l_player.next_order();
            }
        }
    }
}