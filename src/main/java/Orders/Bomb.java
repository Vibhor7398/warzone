/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 2.0
 */
package Orders;

import Models.Country;
import Models.Player;

import java.util.Collection;

/**
 * Represents a Bomb order in the game, allowing a player to halve the army units in a target country.
 */
public class Bomb implements Order {
    /**
     * The player issuing the bomb order.
     */
    Player d_player;
    /**
     * The target country to be bombed.
     */
    Country d_country;

    /**
     * Constructs a Bomb order with the specified player and target country.
     *
     * @param p_player The player issuing the bomb order.
     * @param p_country The country that will be bombed.
     */
    public Bomb(Player p_player, Country p_country){
        this.d_player = p_player;
        this.d_country = p_country;
    }


    /**
     * Validates if the bomb order can be executed. Checks if the target country exists, if the player exists,
     * if the player owns the bomb card, if the target country does not belong to the player, and if the target
     * country is adjacent to any country owned by the player.
     *
     * @return true if the order is valid; false otherwise.
     */
    @Override
    public boolean isValid() {
        if(d_country==null || d_player == null){
            return false;
        }
        // Check if the player has the bomb card
        if(!d_player.getCardList().contains("Bomb")){
            System.out.println("player "+d_player.getName()+"doesn't have the bomb card! ");
            return false;
        }

        // Check if the country belongs to the player
        if (d_player.getCountriesOwned().contains(d_country)) {
            System.out.println("This country belongs to you");
            return false;
        }

        // Check if the country is a neighbor of any country owned by the player
        for (Country playerCountry : d_player.getCountriesOwned()) {
            if (playerCountry.getNeighbors().containsValue(d_country)) {
                return true;
            }
        }

        // If no neighboring country found, return false
        return false;
    }


    /**
     * Executes the bomb order if it is valid, halving the number of army units in the target country.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_country.setArmies(d_country.getArmies() / 2);
            d_player.removeCard("Bomb");
            print();
        } else {
            System.out.println("Invalid Order! ");
        }
    }

    /**
     * Prints the result of the bomb order, showing the updated army count in the target country.
     */
    @Override
    public void print() {
        System.out.println("Bombing is successful on " + d_country.getName() + " by " + d_player.getName());
        System.out.println("Armies on " + d_country.getName() + " after bombing is " + d_country.getArmies());
    }
}
