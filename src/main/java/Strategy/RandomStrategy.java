package Strategy;

import Models.Country;
import Models.Player;
import Orders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implements a random strategy for a player in a game similar to Risk.
 * This strategy makes random decisions for deploying armies, attacking, and moving armies.
 */
public class RandomStrategy extends PlayerStrategy{
    Random l_random = new Random();
    /**
     * Constructs a RandomStrategy with a specified player and a list of countries owned by the player.
     *
     * @param p_player The player adopting this strategy.
     * @param p_country The list of countries owned by the player.
     */
    public RandomStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    /**
     * Creates an order for the player using the random strategy.
     * This strategy involves random deployment of armies and random selection of either attacking or moving armies.
     *
     * @return An Order object representing the action taken by the player.
     */
    @Override
    public Order createOrder() {
        if(d_player.getCountriesOwned().isEmpty()){
            return null;
        }

        Country l_randomCountry = d_player.getCountriesOwned().get(l_random.nextInt(d_player.getCountriesOwned().size()));

        if(d_player.getArmies()>0){
            d_player.setD_orderList(new Deploy(d_player, l_randomCountry, d_player.getArmies()));
        }

        int l_randomValue = 0;
        if(d_player.getCardList().contains("Airlift")) {
            l_randomValue = 1;
        } else if(d_player.getCardList().contains("Bomb")) {
            l_randomValue = 2;
        } else if(d_player.getCardList().contains("Blockade")) {
            l_randomValue = 3;
        }


        return switch (l_randomValue) {
            case (0) -> new Advance(d_player, toAttackFrom(), toAttack(), (toAttackFrom().getArmies()));
            case (1) -> new Airlift(d_player, toMoveFrom(), toDefend(), (toDefend().getArmies()));
            case (2) -> new Bomb(d_player, toAttack());
            case (3) -> new Blockade(d_player, toMoveFrom());
            default -> null;
        };
    }

    /**
     * Selects a random country to attack.
     *
     * @return The country selected for attack.
     */
    @Override
    protected Country toAttack() {
        Country l_toAttackFrom = toAttackFrom();
        List<Country> l_countryList = new ArrayList<>(l_toAttackFrom.getNeighbors().values());
        for(Country l_country : l_countryList){
            if(l_toAttackFrom.getNeighbors().containsValue(l_country)){
                return l_country;
            }
        }
        return l_countryList.get(l_random.nextInt(l_countryList.size()));
    }

    /**
     * Selects a random country owned by the player to attack from.
     *
     * @return The country selected for launching an attack.
     */
    @Override
    protected Country toAttackFrom() {
        return d_player.getCountriesOwned().get(l_random.nextInt(d_player.getCountriesOwned().size()));
    }

    /**
     * Selects a random country owned by the player to move armies from.
     *
     * @return The country selected for moving armies from.
     */
    @Override
    protected Country toMoveFrom() {
        return d_player.getCountriesOwned().get(l_random.nextInt(d_player.getCountriesOwned().size()));
    }

    /**
     * Selects a random neighboring country to defend.
     *
     * @return The neighboring country selected for defense.
     */
    @Override
    protected Country toDefend() {
        Country l_toMoveFrom = toMoveFrom();
        List<Country> l_countryList = new ArrayList<>(l_toMoveFrom.getNeighbors().values());
        for(Country l_country : l_countryList){
            if(l_toMoveFrom.getNeighbors().containsValue(l_country)){
                return l_country;
            }
        }
        return l_countryList.get(l_random.nextInt(l_countryList.size()));
    }
}
