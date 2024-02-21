/**
 *
 * 
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */

package Models;

import Services.CommandValidationService;
import java.util.*;

/**
 * Represents a player in the game.
 */
public class Player {
    private String d_name;
    private static int d_ReinforcementsCompleted;
    private int d_armiesCount;
    private ArrayList<Country> d_countriesOwned;
    private Queue<String> d_orderArgs;

    /**
     * Constructs a player with the given name.
     *
     * @param p_name The name of the player.
     */
    public Player(String p_name) {
        this.setName(p_name);
        d_armiesCount=0;
        d_countriesOwned = new ArrayList<>();
        d_orderArgs = new LinkedList<>();
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

    /**
     * Sets the order for the player.
     *
     * @param p_orderArgs The arguments of the order.
     */
    public void setOrder(String p_orderArgs) {
        d_orderArgs.add(p_orderArgs);
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
     * Allows the player to issue orders for reinforcement deployment.
     * The method prompts the player to issue orders for deploying reinforcements based on their available armies
     * and the countries they own. It ensures that the issued orders are valid before processing them.
    */
    public void issue_order() {
        Scanner l_sc =  new Scanner(System.in);
        CommandValidationService l_cvs = new CommandValidationService();
        ArrayList<String> l_countriesOwned = new ArrayList<>();
        
        // Populate the list of country names owned by the player
        for (Country l_country : this.getCountriesOwned()) {
            l_countriesOwned.add(l_country.getName());
        }
        boolean l_valid = false;

        // Continue prompting for orders until a valid order is issued
        while (!l_valid) {
            l_valid=true;
            if (this.getArmies() != 0) {
                System.out.println(this.getName() + " please issue your orders: ");
                System.out.println("Reinforcement(s) available for deployment: " + this.getArmies());
                String l_order = l_sc.nextLine();
                String[] l_orderArgs = l_order.trim().split("\\s+");

                if (!l_cvs.validateDeployCommand(l_orderArgs)) {
                    System.out.print("Invalid Command! ");
                    l_valid=false;
                } else {
                    String l_countryName = l_orderArgs[1];
                    if (!l_countriesOwned.contains(l_countryName)) {
                        System.out.println("This country does not belong to you");
                        l_valid=false;
                    }else if (checkArmyExceeded(Integer.parseInt(l_orderArgs[2]),this.getArmies())) { //Integer.parseInt(l_orderArgs[2]) > this.getArmies()
                        System.out.println("Not having enough armies");
                        l_valid=false;
                    } else if (Integer.parseInt(l_orderArgs[2]) < 1) {
                        System.out.println("Number of armies should be greater than 0:");
                        l_valid = false;
                    } else {
                        
                        // Process the valid order
                        this.setArmies(this.getArmies() - Integer.parseInt(l_orderArgs[2]));
                        if (this.getArmies() == 0) {
                            d_ReinforcementsCompleted++;
                        }
                        setOrder(l_order);
                    }
                }
            }
        }
    }

    /**
     * Checks if the number of armies specified in a command exceeds the available army count.
     *
     * @param l_cmd      The number of armies specified in the command.
     * @param armyCount  The available army count.
     * @return True if the number of armies exceeds the available count, false otherwise.
    */
    public boolean checkArmyExceeded(int l_cmd, int armyCount){
        if(l_cmd > armyCount ){
            System.out.println("Not having enough armies");
            return true;
        }
        return false;
    }

    /**
     * Handles the deployment of armies to a country based on the given command.
     *
     * @param p_command The command containing the country name and the number of armies to deploy.
    */
    private void handleDeployOrder(ArrayList<String> p_command) {
        Order l_order = new Order();
        l_order.deployOrder(this, p_command.get(0), Integer.parseInt(p_command.get(1)));
    }
    
    /**
     * Handles an invalid command by printing a message indicating that the command is invalid.
     */
    private void handleInvalidCommand() {
        System.out.println("Invalid Command! --- Command is \"deploy countryID num\"");
    }

    /**
     * Processes the next order in the order queue.
     * This method dequeues the next order from the order queue, processes it, and updates the game state accordingly.
     */
    public void next_order() {
        // Check if there are orders to process
        if (!d_orderArgs.isEmpty()) {

            // Dequeue the next order command
            String l_orderCommand = d_orderArgs.poll();

            // If the order queue is empty after dequeuing, increment the count of completed reinforcements
            if(d_orderArgs.isEmpty()){
                d_ReinforcementsCompleted++;
            }
            // Split the order command into individual components
            String[] l_orderCommandList = l_orderCommand.trim().split("\\s+");
            ArrayList<String> l_commandList = new ArrayList<>(Arrays.asList(l_orderCommandList));
            String d_orderType = l_orderCommandList[0];
            
            // Remove the order type from the command list
            if (!l_commandList.isEmpty()) {
                l_commandList.removeFirst();
            }
            // Process the order based on its type
            switch (d_orderType) {
                case "deploy":
                    handleDeployOrder(l_commandList);
                    break;
                default:
                    handleInvalidCommand();
                    break;
            }
        }
    }
}