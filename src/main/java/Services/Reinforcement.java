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
            l_finalReinforceCount = Math.max(l_noOfCountriesOwned/3 , 3);
            LinkedHashMap<String,Continent> l_map = MapsController.getContinents();
            for(Continent l_continent : l_map.values()){
                l_isAllCountriesOwned = true;
                for(Country l_country : l_continent.getCountries().values()){
                    if(!l_player.getCountriesOwned().contains(l_country)){
                        l_isAllCountriesOwned = false;
                        break;
                    }
                }
                if(l_isAllCountriesOwned){
                    l_finalReinforceCount += l_continent.getContinentValue();
                }
            }
            l_player.setArmies(l_player.getArmies()+l_finalReinforceCount);
        }
    }
}