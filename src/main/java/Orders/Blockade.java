/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 2.0
 */
package Orders;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;

/**
 * Represents a Blockade order in the game.
 * This order triples the number of armies in the specified country
 * and makes it neutral, removing it from the player's control.
 */
public class Blockade implements Order{
    /**
     * The player issuing the blockade order.
     */

    Player d_player;

    /**
     * The country to be blockaded.
     */
    Country d_country;

    /**
     * Constructs a Blockade order.
     *
     * @param p_player The player issuing the blockade.
     * @param p_country The country to be blockaded.
     */
    public Blockade(Player p_player, Country p_country){
        this.d_player = p_player;
        this.d_country = p_country;
    }


    /**
     * Validates if the blockade order can be executed.
     * Checks if the country exists, if the player exists, if the player owns the blockade card,
     * and if the player owns the country to be blockaded.
     *
     * @return true if the order is valid; false otherwise.
     */
    @Override
    public boolean isValid() {
        if(d_country==null || d_player == null){
            return false;
        }
        if(!d_player.getCardList().contains("Blockade")){
            System.out.println("player "+d_player.getName()+"doesn't have the blockade card! ");
            GameEngineController.d_Log.notify("player "+d_player.getName()+"doesn't have the blockade card! ");
            return false;
        } else if (d_player.getCountriesOwned().contains(d_country)) {
            return true;
        } else {
            System.out.println("This country does not belong to you");
            GameEngineController.d_Log.notify("This country does not belong to you");
            return false;
        }
    }

    /**
     * Executes the blockade order if it is valid.
     * Triples the armies in the country and removes it from the player's control.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_country.setArmies(d_country.getArmies() * 3);
            d_player.removeCountryFromCountriesOwned(d_country);
            d_player.removeCard("Blockade");
            print();
        } else {
            System.out.println("Invalid Order! ");
            GameEngineController.d_Log.notify("Invalid Blockade Order! by "+d_player.getName());
        }
    }

    /**
     * Prints the result of the blockade order.
     * Displays the new army count in the blockaded country.
     */
    @Override
    public void print() {
        System.out.println("Blockade is successful on " + d_country.getName() + " by " + d_player.getName());
        GameEngineController.d_Log.notify("Blockade is successful on " + d_country.getName() + " by " + d_player.getName());
        System.out.println("Armies on " + d_country.getName() + " after blockade is " + d_country.getArmies());
        GameEngineController.d_Log.notify("Armies on " + d_country.getName() + " after blockade is " + d_country.getArmies());
    }
}
