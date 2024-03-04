/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Services;

import Controller.MapsController;
import Models.Continent;
import Models.Country;
import Models.Player;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class provides functionality for assigning reinforcements to players in a game.
 */
public class Reinforcement {

    /**
     * Assigns reinforcements to players based on the number of countries owned and continents controlled.
     * @param p_playerList The list of players to whom reinforcements are to be assigned.
     */
    public static void assignReinforcements(ArrayList<Player> p_playerList){
        for(Player l_player : p_playerList){
            int l_noOfCountriesOwned = l_player.getCountriesOwned().size();
            int l_finalReinforceCount;
            boolean l_isAllCountriesOwned;
            // Calculate the base reinforcement count based on the number of countries owned
            l_finalReinforceCount = Math.max(l_noOfCountriesOwned/3 , 3);

            // Get the map of continents from the controller
            LinkedHashMap<String,Continent> l_map = MapsController.getContinents();

            for(Continent l_continent : l_map.values()){
                l_isAllCountriesOwned = true;

                // Check if all countries in the continent are owned by the player
                for(Country l_country : l_continent.getCountries().values()){
                    if(!l_player.getCountriesOwned().contains(l_country)){
                        l_isAllCountriesOwned = false;
                        break;
                    }
                }

                // If all countries in the continent are owned by the player, add the continent value to the final reinforcement count
                if(l_isAllCountriesOwned){
                    l_finalReinforceCount += l_continent.getContinentValue();
                }
            }

            // Add the calculated reinforcement count to the player's armies
            l_player.setArmies(l_player.getArmies()+l_finalReinforceCount);
        }
    }
}