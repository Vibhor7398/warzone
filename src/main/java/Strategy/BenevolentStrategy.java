package Strategy;

import Models.Country;
import Models.Player;
import Orders.Advance;
import Orders.Airlift;
import Orders.Deploy;
import Orders.Order;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Implements a benevolent strategy for a player in a game similar to Risk.
 * This strategy focuses on fortifying the player's weakest countries by deploying
 * available armies to them or using airlift cards to move armies between their countries.
 */
public class BenevolentStrategy extends PlayerStrategy{
    Random d_random = new Random();
    public BenevolentStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Creates an order for the player using the benevolent strategy. It deploys available armies
     * to the weakest country owned by the player. If an Airlift card is available, it uses it
     * to move armies from the country with the most armies to the weakest one.
     *
     * @return An Order object representing the action taken by the player.
     */

    @Override
    public Order createOrder() {

        Country l_toDefend = toDefend();
        Country l_toMoveFrom = toMoveFrom();

        if(d_player.getCountriesOwned().isEmpty() || l_toDefend == null || l_toMoveFrom == null){
            return null;
        }

        if (d_player.getArmies() > 0) {
            d_player.setD_orderList(new Deploy(d_player, l_toDefend, d_player.getArmies()));
        }

        if (d_player.getCardList().contains("Airlift")) {
            return new Airlift(d_player, l_toMoveFrom, l_toDefend, l_toMoveFrom.getArmies());
        } else {
            return new Advance(d_player, l_toMoveFrom, l_toDefend, l_toMoveFrom.getArmies());
        }
    }

    /**
     * Not implemented for benevolent strategy as it focuses on defense and support.
     *
     * @return null Always returns null since attacking is not part of the benevolent strategy.
     */
    @Override
    protected Country toAttack() {
        return null;
    }


    /**
     * Not implemented for benevolent strategy as it does not focus on attacking.
     *
     * @return null Always returns null since selecting a country to attack from is not part of the benevolent strategy.
     */
    @Override
    protected Country toAttackFrom() {
        return null;
    }

    /**
     * Selects the country with the most armies that is also a neighbor to another owned country.
     * This country is considered for moving armies from, to support weaker countries.
     *
     * @return The country from which armies should be moved to support weaker territories.
     */
    @Override
    protected Country toMoveFrom() {
        List<Country> l_countryListAirlift = new ArrayList<>(d_player.getCountriesOwned());
        List<Country> l_countryList = new ArrayList<>(toDefend().getNeighbors().values());

        if(d_player.getCardList().contains("Airlift")){
            Country l_maxArmyAirlift = l_countryListAirlift.get(d_random.nextInt(d_player.getCountriesOwned().size()));
            for(Country l_c : l_countryListAirlift){
                if(l_c.getArmies() > l_maxArmyAirlift.getArmies()){
                    l_maxArmyAirlift = l_c;
                }
            }
            return l_maxArmyAirlift;
        } else {
            Country l_maxArmy = null;
            for(Country l_c : l_countryList){
                if(l_c.getNeighbors().containsValue(toDefend())){
                    l_maxArmy = l_c;
                }
            }
            for(Country l_c : l_countryList){
                if(l_maxArmy==null){
                    return null;
                }
                if(l_c.getArmies() > l_maxArmy.getArmies() && l_c.getNeighbors().containsValue(toDefend())){
                    l_maxArmy = l_c;
                }
            }
            return l_maxArmy;
        }
    }

    /**
     * Determines the weakest country owned by the player, to prioritize its defense.
     * This is calculated by finding the country with the least number of armies.
     *
     * @return The weakest country owned by the player.
     */
    @Override
    protected Country toDefend() {
        List<Country> l_countryList = new ArrayList<>(d_player.getCountriesOwned());//d_player.getCountriesOwned();
        Country l_minArmy = l_countryList.get(d_random.nextInt(l_countryList.size()));

        for(Country l_country : l_countryList){
            if(l_minArmy.getArmies()>l_country.getArmies()){
                l_minArmy = l_country;
            }
        }
        return l_minArmy;
    }
}
