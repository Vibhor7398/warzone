package Strategy;

import Models.Country;
import Models.Player;
import Orders.Order;

import java.util.List;


/**
 * The {@code PlayerStrategy} abstract class represents the strategy pattern for a player in the game.
 * It provides a framework for defining different types of strategies that players can adopt during gameplay.
 * Each player strategy determines how players create orders, choose countries to attack or defend, and decide
 * from which countries to launch attacks or move armies.
 */
public abstract class PlayerStrategy {
    /**
     * A list of countries owned by the player.
     */
    List<Country> d_country;
    /**
     * The player adopting this strategy.
     */
    Player d_player;

    /**
     * Constructs a {@code PlayerStrategy} instance with specified player and countries.
     *
     * @param p_player The player who is adopting this strategy.
     * @param p_country A list of countries owned by the player.
     */
    PlayerStrategy(Player p_player, List<Country> p_country){
        d_player = p_player;
        d_country = p_country;
    }

    /**
     * Creates and returns an order based on the strategy adopted by the player.
     * The specific implementation of this method will vary depending on the concrete strategy class.
     *
     * @return An {@code Order} object representing the next action the player wants to take.
     */
    public abstract Order createOrder();

    /**
     * Determines the country to attack based on the strategy.
     * The choice of country is strategy-specific and will be implemented by subclasses.
     *
     * @return The {@code Country} to be attacked.
     */
    protected abstract Country toAttack();
    /**
     * Determines the country from which to launch an attack based on the strategy.
     * This method's implementation is specific to the strategy adopted by the player.
     *
     * @return The {@code Country} from which the attack will be launched.
     */
    protected abstract Country toAttackFrom();

    /**
     * Determines the country from which to move armies based on the strategy.
     * The decision is influenced by the strategy's objectives and current game state.
     *
     * @return The {@code Country} from which armies will be moved.
     */
    protected abstract Country toMoveFrom();

    /**
     * Identifies a country to defend based on the strategy.
     * The choice is dependent on the strategy's defensive priorities and tactics.
     *
     * @return The {@code Country} that should be defended.
     */
    protected abstract Country toDefend();

}
