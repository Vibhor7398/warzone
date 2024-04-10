/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Orders;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;

/**
 * Airlift class represents an order to move a specified number of armies from one country to another using the Airlift card.
 * Airlift order can only be executed if the player possesses the Airlift card, owns both the source and target countries, has sufficient armies in the source country, and the source and target countries are not the same.
 */
public class Airlift implements Order {

    /**
     * The source country from which the armies are airlifted.
     */
    Country d_sourceCountry;

    /**
     * The target country to which the armies are airlifted.
     */
    Country d_targetCountry;

    /**
     * The number of armies to be airlifted.
     */
    int d_armyToBeAirlift;

    /**
     * The player who initiates the airlift order.
     */
    Player d_player;

    /**
     * Constructs an Airlift order with the specified player, source country, target country, and number of armies to be airlifted.
     *
     * @param p_player The player initiating the airlift order.
     * @param p_sourceCountry The source country from which the armies are airlifted.
     * @param p_targetCountry The target country to which the armies are airlifted.
     * @param p_armyToBeAirlift The number of armies to be airlifted.
     */
    public Airlift(Player p_player, Country p_sourceCountry, Country p_targetCountry, int p_armyToBeAirlift) {
        this.d_armyToBeAirlift = p_armyToBeAirlift;
        this.d_sourceCountry = p_sourceCountry;
        this.d_targetCountry = p_targetCountry;
        this.d_player = p_player;
    }

    /**
     * Checks whether the Airlift order is valid.
     *
     * @return true if the Airlift order is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        if(d_sourceCountry==null || d_player == null || d_targetCountry==null){
            return false;
        }
        if (!d_player.getCardList().contains("Airlift")) {
            System.out.println("player " + d_player.getName() + " doesn't have the airlift card! ");
            GameEngineController.d_Log.notify("player " + d_player.getName() + " doesn't have the airlift card! ");
            return false;
        }
        if (!d_player.getCountriesOwned().contains(d_targetCountry)) {
            System.out.println("Player " + d_player.getName() + " does not own the target country " + d_targetCountry.getName());
            GameEngineController.d_Log.notify("Player " + d_player.getName() + " does not own the target country " + d_targetCountry.getName());
            return false;
        }
        if (!d_player.getCountriesOwned().contains(d_sourceCountry)) {
            System.out.println("Player " + d_player.getName() + " does not own the source country " + d_sourceCountry.getName());
            GameEngineController.d_Log.notify("Player " + d_player.getName() + " does not own the source country " + d_sourceCountry.getName());
            return false;
        }
        if (d_sourceCountry.getName().equals(d_targetCountry.getName())) {
            System.out.println("Source and Target country must not be the same ");
            GameEngineController.d_Log.notify("Source and Target country must not be the same ");
            return false;
        }
        if (d_sourceCountry.getArmies() < d_armyToBeAirlift) {
            System.out.println(d_player.getName() + " does not have sufficient armies on country " + d_sourceCountry.getName() + " to airlift to target country " + d_targetCountry.getName());
            GameEngineController.d_Log.notify(d_player.getName() + " does not have sufficient armies on country " + d_sourceCountry.getName() + " to airlift to target country " + d_targetCountry.getName());
            return false;
        }
        return true;
    }

    /**
     * Executes the Airlift order if it is valid.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_sourceCountry.setArmies(d_sourceCountry.getArmies() - d_armyToBeAirlift);
            System.out.println("Armies remaining on source country " + d_sourceCountry.getName() + " after airlift is " + d_sourceCountry.getArmies());
            GameEngineController.d_Log.notify("Armies remaining on source country " + d_sourceCountry.getName() + " after airlift is " + d_sourceCountry.getArmies());
            d_targetCountry.setArmies(d_targetCountry.getArmies() + d_armyToBeAirlift);
            System.out.println("Armies available on target country " + d_targetCountry.getName() + " after airlift is " + d_targetCountry.getArmies());
            GameEngineController.d_Log.notify("Armies available on target country " + d_targetCountry.getName() + " after airlift is " + d_targetCountry.getArmies());
            d_player.removeCard("Airlift");
            print();
        } else {
            System.out.println("Invalid Airlift Order! by "+d_player.getName());
            GameEngineController.d_Log.notify("Invalid Airlift Order! by "+d_player.getName());
        }
    }

    /**
     * Prints a message indicating the successful execution of the Airlift order.
     */
    @Override
    public void print() {
        System.out.println(d_player.getName() + " has successfully airlifted the number of armies: " + d_armyToBeAirlift + " from source country " + d_sourceCountry.getName() + " to target country " + d_targetCountry.getName() + ".");
        GameEngineController.d_Log.notify(d_player.getName() + " has successfully airlifted the number of armies: " + d_armyToBeAirlift + " from source country " + d_sourceCountry.getName() + " to target country ");
    }
}
