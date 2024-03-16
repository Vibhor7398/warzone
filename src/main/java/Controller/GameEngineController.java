/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */

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

/**
 * Controls the game engine functionality.
 * This class manages the game's core operations, including map loading, player actions, and game flow.
 */

public class GameEngineController {
    public static ArrayList<Player> d_Players;
    private static MapsController d_Map;

    /**
     * Constructs a new instance of GameEngineController.
     * Initializes the game map and player list.
     */
    public GameEngineController(){
        d_Map = new MapsController();
        d_Players = new ArrayList<>();
    }

    public GameEngineController(MapsController p_mc){
        d_Map = p_mc;
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

    /**
     * Loads a map from a file and validates it.
     * This method attempts to load a map from the specified file, validates it, and checks if the map is valid.
     * If the map is invalid, a message indicating the same is printed.
     *
     * @param p_filename The name of the file containing the map to be loaded.
     */
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

    /**
     * Displays the details of the current game map.
     * This method invokes the `showMap` method of the game map object to display the details of the map,
     * including continents, countries, and their respective neighbors.
     */
    private void executeShowMap(){
        d_Map.showMap();
    }

    /**
     * Saves the current game map to a file.
     * This method validates the map, checks if it is valid, and then saves it to the specified file.
     * If the map is invalid, an error message is displayed.
     *
     * @param p_filename The name of the file to which the map will be saved.
    */
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


    /**
     * Edits the current game map using data from the specified file.
     * This method attempts to edit the map by reading data from the given file and updating the map accordingly.
     * If the file cannot be read or the map editing fails, an error message is displayed.
     *
     * @param p_filename The name of the file containing the map data to be used for editing.
    */
    private void executeEditMap(String p_filename){
        File l_file = new File(AppConstants.MapsPath + p_filename);
        try {
            d_Map.editMap(l_file);
        } catch (IOException l_e) {
            System.out.println("Map could not be created. " + l_e.getMessage());
        }
    }

    /**
     * Adds a new continent to the game map.
     *
     * @param p_continentID     The unique identifier for the new continent.
     * @param p_continentValue  The control value associated with the new continent.
     */
    private void executeAddContinent(String p_continentID, int p_continentValue){
        d_Map.addContinent(p_continentID,p_continentValue);
    }


    /**
     * Removes a continent from the game map.
     *
     * @param p_continentID The unique identifier of the continent to be removed.
     * 
    */
    private void executeRemoveContinent(String p_continentID){
        d_Map.removeContinent(p_continentID);
    }

    /**
     * Adds a new country to the game map.
     *
     * @param p_countryID     The unique identifier for the new country.
     * @param p_continentID   The unique identifier of the continent to which the new country belongs.
    */
    private void executeAddCountry(String p_countryID, String p_continentID){
        d_Map.addCountry(p_countryID,p_continentID);
    }


    /**
     * Removes a country from the game map.
     *
     * @param p_countryID The unique identifier of the country to be removed.
    */
    private void executeRemoveCountry(String p_countryID){
        d_Map.removeCountry(p_countryID);
    }

    /**
     * Adds a neighbor to a country on the game map.
     *
     * @param p_countryID The unique identifier of the country to which the neighbor will be added.
     * @param p_neighborCountryID The unique identifier of the neighbor country to be added.
    */
    private void executeAddNeighbor(String p_countryID, String p_neighborCountryID){
        d_Map.editNeighbors("add", p_countryID, p_neighborCountryID);
    }

    /**
     * Removes a neighbor from a country on the game map.
     *
     * @param p_countryID The unique identifier of the country from which the neighbor will be removed.
     * @param p_neighborCountryID The unique identifier of the neighbor country to be removed.
    */
    private void executeRemoveNeighbor(String p_countryID, String p_neighborCountryID){
        d_Map.editNeighbors("remove", p_countryID, p_neighborCountryID);
    }

    /**
     * Validates the current game map.
     * This method validates the map by checking for various criteria such as continent connectivity,
     * country connectivity, and other map integrity rules. It then prints the status of the map,
     * indicating whether it is valid or invalid.
    */
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

    /**
     * Adds a new player to the game.
     * This method checks if the provided player name already exists in the list of players.
     * If the player name already exists, a message indicating the same is printed.
     * Otherwise, a new player with the provided name is added to the list of players.
     *
     * @param p_gamePlayer The name of the player to be added.
    */
    private void executeAddGamePlayer(String p_gamePlayer){
        int l_playerIndex = doesPlayerExists(p_gamePlayer);
        if (l_playerIndex != -1){
            System.out.println("Player name already exists!");
        }
        else{
            d_Players.add(new Player(p_gamePlayer));
        }
    }

    /**
     * Checks if a player with the provided name already exists in the list of players.
     *
     * @param p_gamePlayer The name of the player to check for existence.
     * @return The index of the player in the list if found, or -1 if the player does not exist.
    */
    private int doesPlayerExists(String p_gamePlayer){
        for(int i = 0 ; i < d_Players.size() ; i++){
            if(d_Players.get(i).getName().equals(p_gamePlayer)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Removes a player from the list of players if the player exists.
     * If the player does not exist, a message indicating the same is printed.
     *
     * @param p_gamePlayer The name of the player to be removed.
    */
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

    /**
     * Executes the deploy phase of the game.
     * In this phase, players issue orders for deploying reinforcements and executing their next orders.
     * The deploy phase continues until all players have completed their reinforcements.
    */
    private void executeDeploy(){
        // Continuously execute orders until all players have completed their reinforcements
        while(Player.getD_reinforcementsCompleted() != d_Players.size()){
            // Issue orders for deploying reinforcements for each player
            for(Player l_player : d_Players){
                l_player.issue_order();
            }
        }
        // Reset the count of completed reinforcements
        Player.setD_reinforcementsCompleted(0);
        
        // Continue executing orders until all players have completed their reinforcements
        while(Player.getD_reinforcementsCompleted() != d_Players.size()){
            // Execute the next orders for each player
            for(Player l_player : d_Players){
                l_player.next_order();
            }
        }
    }
}