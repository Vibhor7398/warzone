package Strategy;

import Models.Country;
import Models.Player;
import Orders.Advance;
import Orders.Deploy;
import Orders.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an aggressive strategy for a player in a risk-like game.
 * This strategy focuses on aggressive actions, primarily attacking.
 * It selects the country with the highest number of armies to attack from
 * and targets the enemy country with the lowest number of armies for attack.
 */
public class AggressiveStrategy extends PlayerStrategy{
    Random d_random = new Random();

    /**
     * Constructs an AggressiveStrategy with a specified player and a list of countries owned by the player.
     *
     * @param p_player The player adopting this strategy.
     * @param p_country The list of countries owned by the player.
     */
    public AggressiveStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Creates an order for the player using the aggressive strategy.
     * If the player has available armies, it will first deploy all available armies to the country
     * with the highest number of armies. Then, it will create an advance order to attack
     * the weakest neighboring country.
     *
     * @return An order (Deploy or Advance) based on the strategy.
     */
    @Override
    public Order createOrder() {
        if(toAttackFrom()==null || toAttack()==null){
            return null;
        }
        Country l_AttackFrom = toAttackFrom();
        Country l_countryToAttack = toAttack();

        if(d_player.getArmies()>0){
            d_player.setD_orderList(new Deploy(d_player, l_AttackFrom, d_player.getArmies()));
        }

        int l_armyForAttack = l_AttackFrom.getArmies() + d_player.getArmies();
        if(l_armyForAttack>0){
            return new Advance(d_player, l_AttackFrom, l_countryToAttack, l_armyForAttack);
        }
        return null;
    }

    /**
     * Determines the country to attack based on the aggressive strategy.
     * It selects the neighboring country of the player's strongest country
     * that is not owned by the player and has the lowest number of armies.
     *
     * @return The country to be attacked.
     */
    @Override
    protected Country toAttack() {
        if(toAttackFrom()==null){
            return null;
        }
        Country l_attackFrom = toAttackFrom();

        for (Country l_countryToAttack : l_attackFrom.getNeighbors().values()) {
            if (l_countryToAttack.getOwner()==null || !l_countryToAttack.getOwner().getName().equals(d_player.getName())) {
                return l_countryToAttack;
            }
        }

        List<Country> l_countryList = new ArrayList<>(l_attackFrom.getNeighbors().values());
        Country l_countryToAttack = l_countryList.get(d_random.nextInt(l_countryList.size()));

        for (Country l_tempCountryToAttack : l_countryList) {
            if(l_tempCountryToAttack.getArmies() > l_countryToAttack.getArmies()){
                l_countryToAttack = l_tempCountryToAttack;
            }
        }

        return l_countryToAttack;
    }

    /**
     * Selects the country from which to launch an attack.
     * This is determined as the country owned by the player with the highest number of armies.
     *
     * @return The country from which to attack.
     */
    @Override
    protected Country toAttackFrom() {
        if(d_player.getCountriesOwned().isEmpty()){
            return null;
        }
        Country l_attackFrom = d_player.getCountriesOwned().get(d_random.nextInt(d_player.getCountriesOwned().size()));
        List<Country> l_countryList = d_player.getCountriesOwned();

        for (Country l_tempCountry : l_countryList) {
            if (l_attackFrom.getArmies() < l_tempCountry.getArmies()) {
                for(Country l_countryNeighbor : l_tempCountry.getNeighbors().values()){
                    if(l_countryNeighbor.getOwner()==null || !l_countryNeighbor.getOwner().getName().equals(d_player.getName())){
                        return l_tempCountry;
                    }
                }
            }
        }
        return l_attackFrom;
    }

    /**
     * Not implemented for aggressive strategy as it focuses solely on attacking.
     *
     * @return null Always returns null as moving armies is not part of the aggressive strategy.
     */
    @Override
    protected Country toMoveFrom() {
        return null;
    }

    /**
     * Not implemented for aggressive strategy as it does not prioritize defending.
     *
     * @return null Always returns null as defending is not part of the aggressive strategy.
     */
    @Override
    protected Country toDefend() {
        return null;
    }
}
