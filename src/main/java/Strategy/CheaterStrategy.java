package Strategy;

import Models.Country;
import Models.Player;
import Orders.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements a cheater strategy for a player in a game similar to Risk.
 * This strategy aims to cheat by stealing opponent's territories and doubling the armies of adjacent enemy territories.
 */
public class CheaterStrategy extends PlayerStrategy{
    /**
     * Constructs a CheaterStrategy with a specified player and a list of countries owned by the player.
     *
     * @param p_player The player adopting this strategy.
     * @param p_country The list of countries owned by the player.
     */
    public CheaterStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Creates an order for the player using the cheater strategy.
     * This strategy involves stealing opponent's territories and doubling the armies of adjacent enemy territories.
     *
     * @return An Order object representing the action taken by the player.
     */
    @Override
    public Order createOrder() {
        List<Country> l_tempCountry = new ArrayList<>();
        for(Country l_cheaterCountry : d_player.getCountriesOwned()){
            for(Country l_cheaterNeighbour : l_cheaterCountry.getNeighbors().values()){
                if(l_cheaterNeighbour.getOwner()!=d_player){
                    Player l_player = l_cheaterNeighbour.getOwner();
                    if(l_player!=null){
                        l_player.removeCountryFromCountriesOwned(l_cheaterNeighbour);
                        l_tempCountry.add(l_cheaterNeighbour);
                    }
                }
            }
        }

        for(Country l_country : l_tempCountry){
            d_player.addCountryToCountriesOwned(l_country);
        }

        for(Country l_cheaterCountry : d_player.getCountriesOwned()){
            for(Country l_cheaterNeighbour : l_cheaterCountry.getNeighbors().values()){
                if(l_cheaterNeighbour.getOwner()!=d_player){
                    l_cheaterNeighbour.setArmies(l_cheaterNeighbour.getArmies() * 2);
                    break;
                }
            }
        }
        return null;
    }

    /**
     * Not implemented for cheater strategy as it does not involve traditional attacks.
     *
     * @return null Always returns null since attacking is not part of the cheater strategy.
     */
    @Override
    protected Country toAttack() {
        return null;
    }

    /**
     * Not implemented for cheater strategy as it does not involve traditional attacks.
     *
     * @return null Always returns null since selecting a country to attack from is not part of the cheater strategy.
     */
    @Override
    protected Country toAttackFrom() {
        return null;
    }

    /**
     * Not implemented for cheater strategy as it focuses on cheating actions, not traditional moves.
     *
     * @return null Always returns null since moving armies is not part of the cheater strategy.
     */
    @Override
    protected Country toMoveFrom() {
        return null;
    }
    /**
     * Not implemented for cheater strategy as it focuses on attacking and cheating, not defense.
     *
     * @return null Always returns null since defending is not part of the cheater strategy.
     */
    @Override
    protected Country toDefend() {
        return null;
    }
}
