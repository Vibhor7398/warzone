package Strategy;

import Models.Country;
import Models.Player;
import Orders.Advance;
import Orders.Deploy;
import Orders.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomStrategy extends PlayerStrategy{
    Random l_random = new Random();
    public RandomStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    @Override
    public Order createOrder() {

        Country l_randomCountry = d_player.getCountriesOwned().get(l_random.nextInt(d_player.getCountriesOwned().size()-1));

        if(d_player.getArmies()!=0){
            d_player.setD_orderList(new Deploy(d_player, l_randomCountry, d_player.getArmies()));
            d_player.setArmies(0);
        }

        int l_randomValue = l_random.nextInt(2);

        return switch (l_randomValue) {
            case (0) -> new Advance(d_player, toAttackFrom(), toAttack(), l_random.nextInt(toAttackFrom().getArmies() - 1));
            case (1) -> new Advance(d_player, toMoveFrom(), toDefend(), l_random.nextInt(toDefend().getArmies() - 1));
            default -> null;
        };
    }

    @Override
    protected Country toAttack() {
        Country l_toAttackFrom = toAttackFrom();
        List<Country> l_countryList = new ArrayList<>(l_toAttackFrom.getNeighbors().values());
        return l_countryList.get(l_random.nextInt(l_countryList.size()-1));
    }

    @Override
    protected Country toAttackFrom() {
        return d_player.getCountriesOwned().get(l_random.nextInt(d_player.getCountriesOwned().size()-1));
    }

    @Override
    protected Country toMoveFrom() {
        return d_player.getCountriesOwned().get(l_random.nextInt(d_player.getCountriesOwned().size()-1));
    }

    @Override
    protected Country toDefend() {
        Country l_toMoveFrom = toMoveFrom();
        List<Country> l_countryList = new ArrayList<>(l_toMoveFrom.getNeighbors().values());
        return l_countryList.get(l_random.nextInt(l_countryList.size()-1));    }
}
