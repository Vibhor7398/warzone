package Services;


import Controller.MapsController;
import Models.Continent;
import Models.Country;
import Models.Player;

import java.util.HashMap;


public class Reinforcement {

    public static void assignReinforce(Player[] p_playerList){
        for(Player l_player : p_playerList){
            int l_noOfCountriesOwned = l_player.getCountriesOwned().size();
            int l_finalReinforceCount;
            boolean l_isAllCountriesOwned;
            String l_playerName = l_player.getName();

            l_finalReinforceCount = Math.max(l_noOfCountriesOwned/3 , 5);

            HashMap<String, Continent> l_mapContinents = new MapsController().getContinents();

            for(Continent l_continent : l_mapContinents.values()){
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
