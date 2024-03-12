package Orders;

import Models.Country;
import Models.Player;

public class Advance implements Order{
    Player d_player;
    int d_advance_armies;
    Country d_source_country;
    Country d_target_country;

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

    }

    @Override
    public void print() {

    }
}
