/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */

package Controller;

import Adapter.DominationMapIO;
import Adapter.MapAdapter;
import Constants.AppConstants;
import GameEngine.GameEngine;
import Logger.LogEntryBuffer;
import Logger.LogHandler;
import Models.Command;
import Models.Country;
import Models.Player;
import Orders.Order;
import Services.CommandValidator;
import Services.Reinforcement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import Exception.InvalidCommandException;

/**
 * Controls the game engine functionality.
 * This class manages the game's core operations, including map loading, player actions, and game flow.
 */

public class GameEngineController {
    /**
     * Represents the list of players participating in the game.
     * This ArrayList stores Player objects.
     */
    public static ArrayList<Player> d_Players;
    /**
     * Represents the MapsController instance used in the game.
     * This variable holds a reference to the MapsController object.
     */
    private static MapsController d_Map;
    /**
     * Represents the MapsController instance used in the game.
     * This variable holds a reference to the MapsController object.
     */
    private static int d_currentPlayer;
    /**
     * Represents the number of completed turns in the game.
     * This variable stores an integer value indicating the total number of completed turns.
     */
    private static int d_completedTurns;

    /**
     * ArrayList containing cards owned by players.
     * Each element in the ArrayList is a Player object.
     */
    public static ArrayList<Player> d_cardsOwnedByPlayer = new ArrayList<>();

    /**
     * Static variable representing a log entry buffer.
     * This buffer is used for storing log entries.
     */
    public static LogEntryBuffer d_Log = new LogEntryBuffer();
    public static LogHandler d_logHandler = new LogHandler(d_Log);

    /**
     * Constructs a new instance of GameEngineController.
     * Initializes the game map and player list.
     */
    public GameEngineController(){
        d_Map = new MapsController();
        d_Players = new ArrayList<>();
    }

    /**
     * Constructs a GameEngineController with the provided MapsController.
     *
     * @param p_mc The MapsController instance to be associated with the GameEngineController.
     */
    public GameEngineController(MapsController p_mc){
        d_Map = p_mc;
    }


    /**
     * Prompts the user for the next command input and processes it.
     * This method reads a command from the standard input, validates it,
     * and executes the corresponding action in the game engine.
     * If an invalid command is entered, the user is prompted again.
     */
    public void nextUserInput() {
        CommandValidator l_cv = new CommandValidator();
        try{
            Scanner l_sc = new Scanner(System.in);
            System.out.println("Enter your command");
            String l_command = l_sc.nextLine();
            Command[] l_val= l_cv.validateCommand(l_command);
            GameEngine.getPhase().execute(l_val);
        } catch (InvalidCommandException e) {
            System.out.println(e.getMessage());
            nextUserInput();
        }
    }

    /**
     * Loads a map from a file and validates it.
     * This method attempts to load a map from the specified file, validates it, and checks if the map is valid.
     * If the map is invalid, a message indicating the same is printed.
     *
     * @param p_filename The name of the file containing the map to be loaded.
     */
    public boolean executeLoadMap(String p_filename){
        try {
            MapAdapter l_adapter=new MapAdapter(new DominationMapIO());
            l_adapter.loadMap(d_Map.getD_maps(),AppConstants.MapsPath + p_filename);
//            d_Map.loadMap(AppConstants.MapsPath + p_filename);
            d_Map.validateMap();
            boolean l_isValid = d_Map.isMapValid();
            if(!l_isValid){
                System.out.println("Map is invalid!");
                return false;
            }
            return true;
        } catch (IOException l_e) {
            System.out.println("Load map failed. Check for map file. " + l_e.getMessage());
            return false;
        }
    }

    /**
     * Displays the details of the current game map.
     * This method invokes the `showMap` method of the game map object to display the details of the map,
     * including continents, countries, and their respective neighbors.
     */
    public void executeShowMap(){
        d_Map.showMap();
    }

    /**
     * Saves the current game map to a file.
     * This method validates the map, checks if it is valid, and then saves it to the specified file.
     * If the map is invalid, an error message is displayed.
     *
     * @param p_filename The name of the file to which the map will be saved.
    */
    public void executeSaveMap(String p_filename){
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
    public void executeEditMap(String p_filename){
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
    public void executeAddContinent(String p_continentID, int p_continentValue){
        d_Map.addContinent(p_continentID,p_continentValue);
    }


    /**
     * Removes a continent from the game map.
     *
     * @param p_continentID The unique identifier of the continent to be removed.
     * 
    */
    public void executeRemoveContinent(String p_continentID){
        d_Map.removeContinent(p_continentID);
    }

    /**
     * Adds a new country to the game map.
     *
     * @param p_countryID     The unique identifier for the new country.
     * @param p_continentID   The unique identifier of the continent to which the new country belongs.
    */
    public void executeAddCountry(String p_countryID, String p_continentID){
        d_Map.addCountry(p_countryID,p_continentID);
    }


    /**
     * Removes a country from the game map.
     *
     * @param p_countryID The unique identifier of the country to be removed.
    */
    public void executeRemoveCountry(String p_countryID){
        d_Map.removeCountry(p_countryID);
    }

    /**
     * Adds a neighbor to a country on the game map.
     *
     * @param p_countryID The unique identifier of the country to which the neighbor will be added.
     * @param p_neighborCountryID The unique identifier of the neighbor country to be added.
    */
    public void executeAddNeighbor(String p_countryID, String p_neighborCountryID){
        d_Map.editNeighbors("add", p_countryID, p_neighborCountryID);
    }

    /**
     * Removes a neighbor from a country on the game map.
     *
     * @param p_countryID The unique identifier of the country from which the neighbor will be removed.
     * @param p_neighborCountryID The unique identifier of the neighbor country to be removed.
    */
    public void executeRemoveNeighbor(String p_countryID, String p_neighborCountryID){
        d_Map.editNeighbors("remove", p_countryID, p_neighborCountryID);
    }

    /**
     * Validates the current game map.
     * This method validates the map by checking for various criteria such as continent connectivity,
     * country connectivity, and other map integrity rules. It then prints the status of the map,
     * indicating whether it is valid or invalid.
    */
    public void executeValidateMap(){
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
    public void executeAddGamePlayer(String p_gamePlayer){
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
    public int doesPlayerExists(String p_gamePlayer){
        for(int i = 0 ; i < d_Players.size() ; i++){
            if(d_Players.get(i).getName().equals(p_gamePlayer)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Adds a Player object representing cards owned by a player to the ArrayList.
     *
     * @param p_cardsOwnedByPlayer The Player object representing cards owned by a player to be added.
     */
    static public void setD_cardsOwnedByPlayer(Player p_cardsOwnedByPlayer) {
        d_cardsOwnedByPlayer.add(p_cardsOwnedByPlayer);
    }


    /**
     * Retrieves the ArrayList containing cards owned by players.
     *
     * @return The ArrayList containing Player objects representing cards owned by players.
     */
    static public ArrayList<Player> getD_cardsOwnedByPlayer() {
        return d_cardsOwnedByPlayer;
    }

    /**
     * Removes a player from the list of players if the player exists.
     * If the player does not exist, a message indicating the same is printed.
     *
     * @param p_gamePlayer The name of the player to be removed.
    */
    public void executeRemoveGamePlayer(String p_gamePlayer){
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
     *
     * @return True if countries are successfully assigned and the game can proceed, false otherwise.
    */
    public boolean executeAssignCountries(){
        // Check if there are at least 2 players to start the game
        if(d_Players.size() < 2){
            System.out.println("Cannot play with less than 2 players");
            return false;
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

        // Assign initial reinforcements to players
        Reinforcement.assignReinforcements(d_Players);
        return true;
    }

    /**
     * Sets orders for the current player based on the given command.
     * If not all players have completed their turns, sets orders for the current player.
     * If all players have completed their turns, executes all orders and proceeds to the reinforcement phase.
     *
     * @param p_cmd The command specifying the action to be taken.
     */
    public void setOrders(Command p_cmd) {

        if(d_completedTurns != d_Players.size()){
            setNextPlayer();
            if(p_cmd.getD_cmd().equals("endturn")){
                d_Players.get(d_currentPlayer).setD_isTurnCompleted(true);
                d_completedTurns++;
                if(!ifTurnsCompleted()){
                    incrementNextPlayer();
                }
                getD_cardsOwnedByPlayer().clear();
                d_Players.get(d_currentPlayer).getNegotiatePlayers().clear();
            }
            else{
                d_Players.get(d_currentPlayer).setOrder(p_cmd);
                d_Players.get(d_currentPlayer).issueOrder();
                incrementNextPlayer();
            }
        }
        else{
            executeAllOrders();
            Reinforcement.assignReinforcements(d_Players);
        }
    }



    private boolean ifTurnsCompleted(){
        if(d_completedTurns == d_Players.size()){
            executeAllOrders();
            Reinforcement.assignReinforcements(d_Players);
            return true;
        }
        return false;
    }

    private void setNextPlayer(){
        while(d_Players.get(d_currentPlayer).getD_isTurnCompleted()){
            d_currentPlayer++;
            if(d_currentPlayer == d_Players.size()){
                d_currentPlayer = 0;
            }
            return;
        }
    }

    /**
     * Increments the index of the next player to take a turn in the game.
     * If the index reaches the end of the player list, it wraps around to the beginning.
     */
    private void incrementNextPlayer(){
        d_currentPlayer++;
        if(d_currentPlayer == d_Players.size()){
            d_currentPlayer = 0;
        }
    }

    /**
     * Executes all orders from players in the game.
     * This method iterates over all players, retrieves their next order,
     * and executes it. It continues this process until all players have
     * communicated their completed orders.
     * Once all orders have been executed, the method resets the game state.
     */
    public void executeAllOrders() {
        Order l_order;
        boolean still_more_orders;
        int l_playersCompleted = 0;
        do {
            still_more_orders = false;
            for (Player p : d_Players) {
                l_order = p.next_order();

                if(l_order!=null){
                    still_more_orders = true;
                    l_order.execute();
                }
                else if(!p.hasCommunicatedCompletedOrders()){
                    l_playersCompleted++;
                    p.setD_hasCommunicatedCompletedOrders(true);
                }
            }
            if(l_playersCompleted < d_Players.size()){
                still_more_orders = true;
            }
        } while (still_more_orders);
        reset();
    }

    /**
     * Resets the game state.
     * This method resets the number of completed turns, the index of the current player,
     * and clears the flags indicating whether each player has communicated completed orders
     * and whether their turn is completed.
     */
    private void reset(){
        d_completedTurns = 0;
        d_currentPlayer = 0;
        for (Player p : d_Players){
            p.setD_hasCommunicatedCompletedOrders(false);
            p.setD_isTurnCompleted(false);
        }
    }

}