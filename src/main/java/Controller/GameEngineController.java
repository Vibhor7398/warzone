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

    /**
    * Executes a command based on the provided command string.
    * The command string is parsed to identify the command and its parameters, then the corresponding action is executed.
    *
    * @param p_command The command string to be executed.
    * @throws ArrayIndexOutOfBoundsException If the command string does not contain enough parameters.
    * @throws NumberFormatException If parsing of numerical parameters fails.
    */
    public void executeCommand(String p_command){
        // Get the base command from the provided command string
        String l_baseCmd = CommandValidationService.getBaseCommand(p_command);

        // Split the command string into an array of command components
        String[] l_cmdArr = p_command.trim().split("\\ ");

        // Execute the corresponding action based on the base command
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
                // Execute add or remove continent action based on the provided sub-command
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddContinent(l_cmdArr[2], Integer.parseInt(l_cmdArr[3]));
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveContinent(l_cmdArr[2]);
                break;
            case "editcountry":
                // Execute add or remove country action based on the provided sub-command
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddCountry(l_cmdArr[2], l_cmdArr[3]);
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveCountry(l_cmdArr[2]);
                break;
            case "editneighbor":
                // Execute add or remove neighbor action based on the provided sub-command
                if(l_cmdArr[1].trim().equals("-add"))
                    executeAddNeighbor(l_cmdArr[2], l_cmdArr[3]);
                else if(l_cmdArr[1].trim().equals("-remove"))
                    executeRemoveNeighbor(l_cmdArr[2], l_cmdArr[3]);
                break;
            case "validatemap":
                executeValidateMap();
                break;
            case "gameplayer":
                // Execute add or remove game player action based on the provided sub-command
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

    /**
    * Assigns countries to players at the start of the game.
    * Each country is assigned to a player in a round-robin fashion.
    * If there are fewer than 2 players, the game cannot start, and the game flag is set accordingly.
    * After assigning countries, the main game loop is initiated, and players are assigned initial reinforcements.
    */
    private void executeAssignCountries(){
        // Check if there are at least 2 players to start the game
        if(d_Players.size() < 2){
            System.out.println("Cannot play with less than 2 players");
            CommandValidationService.setD_hasGameStarted(false);
            return;
        }
        // Get the list of countries from the map
        HashMap<String, Country> l_listOfCountries = d_Map.getD_countries();

        // Get the total number of players
        int l_NumPlayers = d_Players.size();

        // Initialize the player index for assigning countries
        int l_playerIndex = 0;

        // Assign countries to players in a round-robin fashion
        for(Country l_country : l_listOfCountries.values()){
            System.out.println("Assigning " + l_country.getName() + " to " + d_Players.get(l_playerIndex).getName());
            d_Players.get(l_playerIndex++).addCountryToCountriesOwned(l_country);
            if(l_playerIndex == l_NumPlayers){
                l_playerIndex = 0;
            }
        }
        System.out.println();
        System.out.println("-----------Main Game Loop---------");
        
        // Set the game flag to indicate that the game has started
        CommandValidationService.setD_hasGameStarted(true);

        // Assign initial reinforcements to players
        Reinforcement.assignReinforcements(d_Players);
        
        // Execute the deploy phase
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