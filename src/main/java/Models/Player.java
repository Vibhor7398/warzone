/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */

package Models;

import Controller.GameEngineController;
import Controller.MapsController;
import Orders.*;
import Strategy.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Represents a player in the game.
 */
public class Player implements Serializable {
    private String d_name;
    private static int d_ReinforcementsCompleted;
    private boolean d_isTurnCompleted = false;
    private int d_armiesCount = 0;
    private ArrayList<Country> d_countriesOwned = new ArrayList<>();
    private Queue<String> d_orderArgs = new LinkedList<>();
    private ArrayList<String> d_cardList = new ArrayList<>();
    private final List<Player> d_NegotiatePlayers = new ArrayList<>();
    private String d_orderType;
    private String[] d_orderArgsValues;
    private ArrayList<Order> d_orderList = new ArrayList<>();
    private Order d_currentOrder;
    private boolean d_hasCommunicatedCompletedOrders = false;
    private Strategy d_playerStrategyType;
    private PlayerStrategy d_playerStrategy;

    /**
     * Constructs a player with the given name.
     *
     * @param p_name The name of the player.
     */
    public Player(String p_name) {
        this.setName(p_name);
    }

    /**
     * Constructs a player with the given name.
     *
     * @param p_name The name of the player.
     */
    public Player(String p_name, Strategy p_strategy) {
        this.setName(p_name);
        this.set_playerStrategyType(p_strategy);
    }

    /**
     * Sets the name of the player.
     *
     * @param p_name The name of the player.
     */
    public void setName(String p_name) {
        this.d_name = p_name;
    }

    /**
     * Retrieves the status indicating whether the turn is completed.
     *
     * @return true if the turn is completed, false otherwise.
     */
    public boolean getD_isTurnCompleted() {
        return d_isTurnCompleted;
    }

    /**
     * Sets the name of the player.
     *
     * @param p_isTurnCompleted Has the turn of the player been completed.
     */
    public void setD_isTurnCompleted(boolean p_isTurnCompleted) {
        this.d_isTurnCompleted = p_isTurnCompleted;
    }

    /**
     * Retrieves the name of the player.
     *
     * @return The name of the player.
     */
    public String getName(){
        return d_name;
    }

     /**
     * Sets the number of armies owned by the player.
     *
     * @param p_armies The number of armies owned by the player.
     */
    public void setArmies(int p_armies){
        d_armiesCount = p_armies;
    }

    /**
     * Retrieves the number of armies owned by the player.
     *
     * @return The number of armies owned by the player.
     */
    public int getArmies(){
        return d_armiesCount;
    }

    /**
     * Retrieves the list of countries owned by the player.
     *
     * @return The list of countries owned by the player.
     */
    public ArrayList<Country> getCountriesOwned() {
        return d_countriesOwned;
    }

    /**
     * Adds a country to the list of countries owned by the player.
     *
     * @param p_country The country to add to the list of countries owned.
     */
    public void addCountryToCountriesOwned(Country p_country) {
        d_countriesOwned.add(p_country);
    }


    /**
     * Adds a card to the player's list of cards.
     * This method is used to give a player a new card, which can be used in the game for various strategic advantages.
     *
     * @param p_card The card to be added to the player's list.
     */
    public void addCard(String p_card){
        d_cardList.add(p_card);
    }


    /**
     * Removes a card from the player's list of cards.
     * This method is used when a player has used a card, and it needs to be removed from their possession.
     *
     * @param p_card The card to be removed from the player's list.
     */
    public void removeCard(String p_card) {
        d_cardList.remove(p_card);
    }

    /**
     * Retrieves the player's list of cards.
     * This method provides access to the cards the player currently holds.
     *
     * @return An ArrayList of strings representing the player's cards.
     */
    public ArrayList<String> getCardList(){
        return d_cardList;
    }

    /**
     * Adds a player to the list of players with whom this player has negotiated peace.
     * This method is used to keep track of players that have agreed not to attack each other for a certain number of turns.
     *
     * @param p_player The player with whom to negotiate peace.
     */
    public void addNegotiatePlayer(Player p_player){
        if (!d_NegotiatePlayers.contains(p_player))
            d_NegotiatePlayers.add(p_player);
    }

    /**
     * Removes a player from the list of players with whom this player has negotiated peace.
     * This method is used when the negotiation agreement comes to an end, allowing for potential conflicts in future turns.
     *
     * @param p_player The player to remove from the negotiation list.
     */
    public void removeNegotiatePlayer(Player p_player){
        d_NegotiatePlayers.remove(p_player);
    }
    
    /**
     * Retrieves the list of players with whom this player has negotiated peace.
     * This method provides access to the list of players that are currently in a peace agreement with this player.
     *
     * @return A list of players with whom peace has been negotiated.
     */
    public List<Player> getNegotiatePlayers(){
        return d_NegotiatePlayers;
    }

    /**
     * Sets the order for the player.
     *
     * @param p_orderArgs The arguments of the order.
     */
    public void setOrder(String p_orderArgs) {
        d_orderArgs.add(p_orderArgs);
    }

    /**
     * Removes a country from the list of countries owned by the player.
     * This method is typically called when a player loses control of a country due to an attack or a diplomatic action.
     *
     * @param p_country The country to be removed from the player's control.
     */
    public void removeCountryFromCountriesOwned(Country p_country) {
        d_countriesOwned.remove(p_country);
    }

    /**
     * Retrieves the number of reinforcements completed by all players.
     *
     * @return The number of reinforcements completed.
     */
    public static int getD_reinforcementsCompleted() {
        return d_ReinforcementsCompleted;
    }

    /**
     * Sets the number of reinforcements completed by all players.
     *
     * @param p_reinforcementsCompleted The number of reinforcements completed.
     */
    public static void setD_reinforcementsCompleted(int p_reinforcementsCompleted) {
        Player.d_ReinforcementsCompleted = p_reinforcementsCompleted;
    }

    /**
     * Sets the order for the player based on the provided command.
     * This method extracts the command type and arguments from the given command object and sets them accordingly.
     *
     * @param p_cmd The command containing the order details.
     */
    public void setOrder(Command p_cmd) {
        d_orderType = p_cmd.getD_cmd();
        d_orderArgsValues = p_cmd.getArgs();
    }
    
    /**
     * Retrieves the arguments associated with the current order.
     *
     * @return An array containing the arguments of the current order.
     */
    public String[] getD_orderArgsValues() {
        return d_orderArgsValues;
    }

    /**
     * Sets the arguments associated with the current order.
     *
     * @param p_orderArgsValues An array containing the arguments of the current order.
     */
    public void setD_orderArgsValues(String[] p_orderArgsValues) {
        this.d_orderArgsValues = p_orderArgsValues;
    }

    /**
     * Sets the list of orders associated with the player.
     *
     * @param p_order The order to be associated with the player.
     */
    public void setD_orderList(Order p_order) {
        this.d_orderList.add(p_order);
    }

    /**
     * Retrieves the current order assigned to the player.
     *
     * @return The current order assigned to the player.
     */
    public Order getD_currentOrder() {
        return d_currentOrder;
    }

    /**
     * Sets the current order assigned to the player.
     *
     * @param p_currentOrder The current order to be assigned to the player.
     */
    public void setD_currentOrder(Order p_currentOrder) {
        this.d_currentOrder = p_currentOrder;
    }

    /**
     * Checks if the player has communicated completed orders.
     *
     * @return True if the player has communicated completed orders; false otherwise.
     */
    public boolean hasCommunicatedCompletedOrders() {
        return d_hasCommunicatedCompletedOrders;
    }

    /**
     * Retrieves the player object by name.
     *
     * @param p_name The name of the player to retrieve.
     * @return The player object corresponding to the provided name.
     */
    public Player getPlayerByName(String p_name){
        for (Player l_player : GameEngineController.d_Players){
            if (l_player.getName().equals(p_name)){
                return l_player;
            }
        }
        d_hasCommunicatedCompletedOrders = true;
        return null;
    }

    /**
     * Returns the player's strategy associated with this class.
     *
     * @return The player's strategy.
     */
    public PlayerStrategy get_playerStrategy() {
        return d_playerStrategy;
    }

    /**
     * Sets the player's strategy.
     *
     * @param p_playerStrategy The player's strategy to set.
     */
    public void set_playerStrategy(PlayerStrategy p_playerStrategy) {
        this.d_playerStrategy = p_playerStrategy;
    }

    /**
     * Sets the player's strategy type.
     *
     * @param p_playerStrategyType The player's strategy type to set.
     */
    public void set_playerStrategyType(Strategy p_playerStrategyType){
        this.d_playerStrategyType = p_playerStrategyType;
    }

    /**
     * Returns the player's strategy type associated with this class.
     *
     * @return The player's strategy type.
     */
    public Strategy get_playerStrategyType(){
        return d_playerStrategyType;
    }

    /**
     * Allows the player to issue orders for reinforcement deployment.
     * The method prompts the player to issue orders for deploying reinforcements based on their available armies
     * and the countries they own. It ensures that the issued orders are valid before processing them.
    */
    public void issueOrder() {
        if(this.get_playerStrategyType().name().equals("Human")){
            switch (d_orderType) {
                case "deploy":
                    System.out.println("Deploy order issued for " + this.getName());
                    GameEngineController.d_Log.notify("Deploy order issued for " + this.getName());
                    setD_currentOrder(new Deploy(this, MapsController.getCountryByName(d_orderArgsValues[0]), Integer.parseInt(d_orderArgsValues[1])));
                    d_orderList.add(getD_currentOrder());
                    break;

                case "advance":
                    System.out.println("Advance order issued for " + this.getName());
                    GameEngineController.d_Log.notify("Advance order issued for " + this.getName());
                    setD_currentOrder(new Advance(this, MapsController.getCountryByName(d_orderArgsValues[0]), MapsController.getCountryByName(d_orderArgsValues[1]), Integer.parseInt(d_orderArgsValues[2])));
                    d_orderList.add(getD_currentOrder());
                    break;

                case "airlift":
                    System.out.println("Airlift order issued for " + this.getName());
                    GameEngineController.d_Log.notify("Airlift order issued for " + this.getName());
                    setD_currentOrder(new Airlift(this, MapsController.getCountryByName(d_orderArgsValues[0]), MapsController.getCountryByName(d_orderArgsValues[1]), Integer.parseInt(d_orderArgsValues[2])));
                    d_orderList.add(getD_currentOrder());
                    break;

                case "bomb":
                    System.out.println("Bomb order issued for " + this.getName());
                    GameEngineController.d_Log.notify("Bomb order issued for " + this.getName());
                    setD_currentOrder(new Bomb(this, MapsController.getCountryByName(d_orderArgsValues[0])));
                    d_orderList.add(getD_currentOrder());
                    break;

                case "blockade":
                    System.out.println("Blockade order issued for " + this.getName());
                    GameEngineController.d_Log.notify("Blockade order issued for " + this.getName());
                    setD_currentOrder(new Blockade(this, MapsController.getCountryByName(d_orderArgsValues[0])));
                    d_orderList.add(getD_currentOrder());
                    break;

                case "negotiate":
                    System.out.println("Negotiate order issued for " + this.getName());
                    GameEngineController.d_Log.notify("Negotiate order issued for " + this.getName());
                    setD_currentOrder(new Diplomacy(this, getPlayerByName(d_orderArgsValues[0])));
                    d_orderList.add(getD_currentOrder());
                    break;

                case "endturn":
                    setD_isTurnCompleted(true);
                    return;

                default:
                    System.out.println("Please enter correct order!");
//                GameEngineController.d_Log.notify("Deploy order issued!");
                    break;
            }
        } else {
            switch (this.get_playerStrategyType().name()) {
                case "Aggressive":
                    set_playerStrategy(new AggressiveStrategy(this, new ArrayList<>(MapsController.getD_countries().values())));
                    setD_currentOrder(get_playerStrategy().createOrder());
                    d_orderList.add(getD_currentOrder());
                    break;

                case "Benevolent":
                    set_playerStrategy(new BenevolentStrategy(this, new ArrayList<>(MapsController.getD_countries().values())));
                    setD_currentOrder(get_playerStrategy().createOrder());
                    d_orderList.add(getD_currentOrder());
                    break;

                case "Cheater":
                    set_playerStrategy(new CheaterStrategy(this, new ArrayList<>(MapsController.getD_countries().values())));
                    setD_currentOrder(get_playerStrategy().createOrder());
                    d_orderList.add(getD_currentOrder());
                    break;

                case "Random":
                    set_playerStrategy(new RandomStrategy(this, new ArrayList<>(MapsController.getD_countries().values())));
                    setD_currentOrder(get_playerStrategy().createOrder());
                    d_orderList.add(getD_currentOrder());
                    break;
            }
        }
    }

    /**
     * Sets whether the player has communicated completed orders.
     *
     * @param d_hasCommunicatedCompletedOrders A boolean value indicating whether the player has communicated completed orders.
     */
    public void setD_hasCommunicatedCompletedOrders(boolean d_hasCommunicatedCompletedOrders) {
        this.d_hasCommunicatedCompletedOrders = d_hasCommunicatedCompletedOrders;
    }

    /**
     * Retrieves the next order to be executed by the player.
     *
     * @return The next order to be executed, or null if there are no more orders.
     */
    public Order next_order(){
        if(!d_orderList.isEmpty()){
            Order l_orderToExecute = d_orderList.getFirst();
            d_orderList.removeFirst();
            return l_orderToExecute;
        }
        return null;
    }

}