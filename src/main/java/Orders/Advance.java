package Orders;

import Models.Country;
import Models.Player;

public class Advance implements Order{
    Player d_player;
    int d_advance_armies;
    Country d_source_country;
    Country d_target_country;
    boolean d_attack_successful;

    public Advance(Player p_player, Country p_country_source, Country p_country_target, int p_advance_num) {
        this.d_player = p_player;
        this.d_source_country = p_country_source;
        this.d_target_country = p_country_target;
        this.d_advance_armies = p_advance_num;
    }
    @Override
    public boolean isValid() {
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

    @Override
    public void execute() {
        if (!isValid()) {
            System.out.println("Army cannot be advanced.");
            return;
        }
        // target country belongs to current player, move the armies to the target country
        if (d_player.getCountriesOwned().contains(d_target_country)) {
            // Removed from source
            d_source_country.setArmies(d_source_country.getArmies() - d_advance_armies);
            // Added to target
            d_target_country.setArmies(d_advance_armies + d_target_country.getArmies());
        } else {
            // target country belongs to another player, initiate an attack
            int l_defenderArmies = d_target_country.getArmies();
            int l_attackerArmies = d_advance_armies;

            if (l_attackerArmies > l_defenderArmies) {
                // Attacker wins, move armies to the target territory
                // Removed from source
                d_source_country.setArmies(d_source_country.getArmies() - d_advance_armies);

                d_target_country.setArmies(l_attackerArmies - l_defenderArmies);
                // todo
                d_player.addCountryToCountriesOwned(d_target_country);

                Player l_target_player = d_target_country.getOwner();
                l_target_player.removeCountryFromCountriesOwned(d_target_country);
                d_attack_successful = true;

            } else {
                // Defender wins, update armies in source territory
                d_source_country.setArmies(d_source_country.getArmies() - d_advance_armies);
                d_attack_successful = false;
            }
        }
        print();

    }

    @Override
    public void print() {
        if(d_attack_successful) {
            System.out.println("Attack successful! " + d_target_country.getName() + " captured by " + d_player.getName());
        } else {
            System.out.println("Attack unsuccessful! " + d_target_country.getName() + " defended successfully.");
        }
        if (d_player.getCountriesOwned().contains(d_target_country)) {
            System.out.println(d_advance_armies + " armies moved from " + d_source_country.getName() + " to " + d_target_country.getName());
        }
    }
    }
}
