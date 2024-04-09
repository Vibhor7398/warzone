package Strategy;

import Models.Country;
import Models.Player;
import Orders.Advance;
import Orders.Deploy;
import Orders.Order;

import java.util.ArrayList;
import java.util.List;

public class AggressiveStrategy extends PlayerStrategy{
    public AggressiveStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    @Override
    public Order createOrder() {
        Country l_AttackFrom = toAttackFrom();

        if(d_player.getArmies()!=0){
            d_player.setD_orderList(new Deploy(d_player, l_AttackFrom, d_player.getArmies()));
            d_player.setArmies(0);
        }

        Country l_countryToAttack = toAttack();
        int l_armyForAttack = l_AttackFrom.getArmies();

        return new Advance(d_player, l_AttackFrom, l_countryToAttack, l_armyForAttack);
    }

    @Override
    protected Country toAttack() {
        Country l_attackFrom = toAttackFrom();

        for (Country l_countryToAttack : l_attackFrom.getNeighbors().values()) {
            if (!l_countryToAttack.getOwner().getName().equals(d_player.getName())) {
                return l_countryToAttack;
            }
        }

        List<Country> l_countryList = new ArrayList<>(l_attackFrom.getNeighbors().values());
        Country l_countryToAttack = l_countryList.getFirst();

        for (Country l_tempCountryToAttack : l_countryList) {
            if(l_tempCountryToAttack.getArmies() > l_countryToAttack.getArmies()){
                l_countryToAttack = l_tempCountryToAttack;
            }
        }

        return l_countryToAttack;
    }

    @Override
    protected Country toAttackFrom() {
        Country l_attackFrom = d_player.getCountriesOwned().getFirst();
        List<Country> l_countryList = d_player.getCountriesOwned();

        for (Country l_tempCountry : l_countryList) {
            if (l_attackFrom.getArmies()< l_tempCountry.getArmies()) {
                l_attackFrom = l_tempCountry;
            }
        }
        return l_attackFrom;
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
