/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */

package Models;

import Controller.GameEngineController;
import Controller.MapsController;
import Orders.*;
import java.util.*;

/**
 * Represents a player in the game.
 */
public class Player {
    private String d_name;
    private static int d_ReinforcementsCompleted;

    private int d_armiesCount = 0;
    private ArrayList<Country> d_countriesOwned = new ArrayList<>();
    private Queue<String> d_orderArgs = new LinkedList<>();
    private ArrayList<String> d_cardList = new ArrayList<>();
    private final List<Player> d_NegotiatePlayers = new ArrayList<>();
    private String d_orderType;
    private String[] d_orderArgsValues;
    private ArrayList<Order> d_orderList;
    private Order d_currentOrder;
    private Command d_command;



    /**
     * Constructs a player with the given name.
     *
     * @param p_name The name of the player.
     */
    public Player(String p_name) {
        this.setName(p_name);
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


    public void addCard(String p_card){
        d_cardList.add(p_card);
    }

    public void removeCard(String p_card) {
        d_cardList.remove(p_card);
    }

    public ArrayList<String> getCardList(){
        return d_cardList;
    }

    public void addNegotiatePlayer(Player p_player){
        if (!d_NegotiatePlayers.contains(p_player))
            d_NegotiatePlayers.add(p_player);
    }

    public void removeNegotiatePlayer(Player p_player){
        d_NegotiatePlayers.remove(p_player);
    }
    /**
     * Sets the order for the player.
     *
     * @param p_orderArgs The arguments of the order.
     */
    public void setOrder(String p_orderArgs) {
        d_orderArgs.add(p_orderArgs);
    }

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

    public void setOrder() {
        d_orderType = d_command.getD_cmd();
        d_orderArgsValues = d_command.getArgs();
    }

    public String[] getD_orderArgsValues() {
        return d_orderArgsValues;
    }

    public void setD_orderArgsValues(String[] p_orderArgsValues) {
        this.d_orderArgsValues = p_orderArgsValues;
    }

    public void setD_orderList(ArrayList<Order> p_orderList) {
        this.d_orderList = p_orderList;
    }

    public Order getD_currentOrder() {
        return d_currentOrder;
    }

    public void setD_currentOrder(Order p_currentOrder) {
        this.d_currentOrder = p_currentOrder;
    }

    public Player getPlayerByName(String p_name){
        for (Player l_player : GameEngineController.d_Players){
            if (l_player.getName().equals(p_name)){
                return l_player;
            }
        }
        return null;
    }

    /**
     * Allows the player to issue orders for reinforcement deployment.
     * The method prompts the player to issue orders for deploying reinforcements based on their available armies
     * and the countries they own. It ensures that the issued orders are valid before processing them.
    */


    public void issueOrder() {

        switch (d_orderType) {
            case "deploy":
                System.out.println("Deploy Order");
                setD_currentOrder(new Deploy(this, MapsController.getCountryByName(d_orderArgsValues[0]), Integer.parseInt(d_orderArgsValues[1])));
                d_orderList.add(getD_currentOrder());
                break;

            case "advance":
                System.out.println("Advance Order");
                setD_currentOrder(new Advance(this, MapsController.getCountryByName(d_orderArgsValues[0]), MapsController.getCountryByName(d_orderArgsValues[1]), Integer.parseInt(d_orderArgsValues[2])));
                d_orderList.add(getD_currentOrder());
                break;

            case "airlift":
                System.out.println("Airlift Order");
                setD_currentOrder(new Airlift(this, MapsController.getCountryByName(d_orderArgsValues[0]), MapsController.getCountryByName(d_orderArgsValues[1]), Integer.parseInt(d_orderArgsValues[2])));
                d_orderList.add(getD_currentOrder());
                break;

            case "bomb":
                System.out.println("Bomb Order");
                setD_currentOrder(new Bomb(this, MapsController.getCountryByName(d_orderArgsValues[0])));
                d_orderList.add(getD_currentOrder());
                break;

            case "blockade":
                System.out.println("Blockade Order");
                setD_currentOrder(new Blockade(this, MapsController.getCountryByName(d_orderArgsValues[0])));
                d_orderList.add(getD_currentOrder());
                break;

            case "negotiate":
                System.out.println("Negotiate Order");
                setD_currentOrder(new Diplomacy(this, getPlayerByName(d_orderArgsValues[0])));
                d_orderList.add(getD_currentOrder());
                break;

            case "end":
                return;

            default:
                System.out.println("Please enter correct order! ");
                break;
        }

    }

}