package Strategy;

import Models.Country;
import Models.Player;
import Orders.Order;

import java.util.List;

public abstract class PlayerStrategy {
    List<Country> d_country;
    Player d_player;

    PlayerStrategy(Player p_player, List<Country> p_country){
        d_player = p_player;
        d_country = p_country;
    }

    public abstract Order createOrder();
    protected abstract Country toAttack();
    protected abstract Country toAttackFrom();
    protected abstract Country toMoveFrom();
    protected abstract Country toDefend();

}
