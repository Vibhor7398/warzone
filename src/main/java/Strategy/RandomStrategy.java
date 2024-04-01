package Strategy;

import Models.Country;
import Models.Player;
import Orders.Order;

import java.util.List;

public class RandomStrategy extends PlayerStrategy{
    public RandomStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    @Override
    public Order createOrder() {
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
        return null;
    }

    @Override
    protected Country toDefend() {
        return null;
    }
}
