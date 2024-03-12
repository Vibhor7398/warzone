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
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public void print() {

    }
}
