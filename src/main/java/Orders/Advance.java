package Orders;

import Models.Country;
import Models.Player;
import Services.CardAssignment;

/**
 * Represents an Advance order in a strategy game. This order can either move armies
 * from one territory to another owned by the same player, or it can initiate an attack
 * on an adjacent territory owned by another player.
 */
public class Advance implements Order{
    /**
     * The player issuing the advance order.
     */
    Player d_player;
    /**
     * The number of armies to be advanced.
     */
    int d_advance_armies;
    /**
     * The source country from where the armies are to be advanced.
     */
    Country d_source_country;
    /**
     * The target country to which the armies are to be advanced or attacked.
     */
    Country d_target_country;
    /**
     * Flag indicating whether an attack was successful.
     */
    boolean d_attack_successful;

    /**
     * Constructs an Advance order with the specified player, source country, target country, and number of armies.
     *
     * @param p_player The player issuing the advance order.
     * @param p_country_source The source country from where the armies are to be advanced.
     * @param p_country_target The target country to which the armies are to be advanced or attacked.
     * @param p_advance_num The number of armies to be advanced.
     */

    public Advance(Player p_player, Country p_country_source, Country p_country_target, int p_advance_num) {
        this.d_player = p_player;
        this.d_source_country = p_country_source;
        this.d_target_country = p_country_target;
        this.d_advance_armies = p_advance_num;
    }

    /**
     * Validates if the advance order can be executed. Checks include verifying ownership of the source country,
     * adjacency of the target country, and the validity of the number of armies to be advanced.
     *
     * @return true if the order is valid, false otherwise.
     */

    @Override
    public boolean isValid() {
        if(d_source_country==null || d_player == null || d_target_country==null){
            return false;
        }
        if (!d_player.getCountriesOwned().contains(d_source_country)) {
            System.out.println("Source country does not belong to the current player.");
            return false;
        }
        // todo - check this
        if (!d_source_country.getNeighbors().containsValue(d_target_country)) {
            System.out.println("Target country is not adjacent to the source country.");
            return false;
        }

        if (d_advance_armies <= 0 || d_advance_armies >= d_source_country.getArmies()) {
            System.out.println("Invalid number of armies to advance.");
            return false;
        }

        if (d_source_country.equals(d_target_country)) {
            System.out.println("Cannot advance armies to the same country.");
            return false;
        }
        return true;
    }


    /**
     * Executes the advance order. This involves either moving armies between territories owned by the player
     * or initiating an attack on an adjacent territory owned by another player.
     */
    @Override
    public void execute() {
        if (!isValid()) {
            System.out.println("Army cannot be advanced.");
            return;
        }
        // target country belongs to current player, move the armies to the target country
        if (d_player.getCountriesOwned().contains(d_target_country)) {
            // Removed army from source country
            d_source_country.setArmies(d_source_country.getArmies() - d_advance_armies);
            // Added army to target country
            d_target_country.setArmies(d_advance_armies + d_target_country.getArmies());
        } else {
            // target country belongs to another player, initiate an attack
            int l_defenderArmies = d_target_country.getArmies();
            int l_attackerArmies = d_advance_armies;
            double l_prob_attacker = (l_attackerArmies * 0.6) % 1 > 0.5 ? Math.ceil(l_attackerArmies * 0.6) : Math.floor(l_attackerArmies * 0.6);
            double l_prob_defender = (l_defenderArmies * 0.7) % 1 > 0.5 ? Math.ceil(l_defenderArmies * 0.7) : Math.floor(l_defenderArmies * 0.7);

           if (l_prob_attacker > l_prob_defender) {
               // Attacker wins, move armies to the target territory
               d_source_country.setArmies(d_source_country.getArmies() - d_advance_armies);
               d_target_country.setArmies(l_attackerArmies - l_defenderArmies);
               d_player.addCountryToCountriesOwned(d_target_country);
               Player l_target_player = d_target_country.getOwner();
               if(l_target_player != null) {                   
                    l_target_player.removeCountryFromCountriesOwned(d_target_country);
               }
               d_attack_successful = true;
               d_player.addCard(CardAssignment.getCard());
           } else {
               // Defender wins, update armies in source territory
               d_source_country.setArmies(d_source_country.getArmies() - d_advance_armies);
               d_target_country.setArmies(d_target_country.getArmies() - d_advance_armies);
               d_attack_successful = false;
           }
       }
        print();
    }

    /**
     * Prints the result of the advance order execution. This includes information about the movement of armies
     * or the outcome of an attack.
     */
    @Override
    public void print() {
        if (d_player.getCountriesOwned().contains(d_target_country)) {
            System.out.println(d_advance_armies + " armies moved from " + d_source_country.getName() + " to " + d_target_country.getName());
            return;
        }
        if(d_attack_successful) {
            System.out.println("Attack successful! " + d_target_country.getName() + " captured by " + d_player.getName());
        } else {
            System.out.println("Attack unsuccessful! " + d_target_country.getName() + " defended successfully.");
        }
    }
}