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

public class BenevolentStrategy extends PlayerStrategy{
    public BenevolentStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    @Override
    public Order createOrder() {

        if (d_player.getArmies() > 0) {
            Country weakestCountry = toDefend();
            if (weakestCountry != null) {
                d_player.setD_orderList(new Deploy(d_player, weakestCountry, d_player.getArmies()));
                d_player.setArmies(0);
            }
        }
        return null;
    }

    @Override
    protected Country toAttack() {
        return null;
    }

    @Override
    protected Country toAttackFrom() {
        return null;
    }

    @Override
    protected Country toMoveFrom() {
            final int threshold = d_player.getCountriesOwned().getFirst().getArmies();
            return d_player.getCountriesOwned().stream()
                    .filter(c -> c.getNeighbors().containsValue(toDefend()))
                    .filter(c -> c.getArmies() > threshold)
                    .max(Comparator.comparingInt(Country::getArmies))
                    .orElse(null);
    }

    @Override
    protected Country toDefend() {
        return null;
    }
}
