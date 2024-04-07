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

        if (d_player.getCardList().contains("Airlift")) {
            ArrayList<Country> l_countries = new ArrayList<>();
            for(Country l_country : d_player.getCountriesOwned()){
                l_countries.add(l_country);
            }
            l_countries.remove(toMoveFrom());
            Country l_weakest_country = l_countries.getFirst();
            for(Country l_country : l_countries){
                if(l_country.getArmies()<l_weakest_country.getArmies()){
                    l_weakest_country = l_country;
                }
            }
            return new Airlift(d_player, toMoveFrom(), l_weakest_country, toMoveFrom().getArmies());
        } else {
            return new Advance(d_player, toMoveFrom(), toDefend(), toMoveFrom().getArmies());
        }
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
        List<Country> l_countryList = d_player.getCountriesOwned();
        Country l_maxArmy = l_countryList.getFirst();
        for(Country l_country : l_countryList){
            if(l_maxArmy.getArmies()<l_country.getArmies() && l_maxArmy.getNeighbors().containsValue(l_country)){
                l_maxArmy = l_country;
            }
        }
        return l_maxArmy;
    }

    @Override
    protected Country toDefend() {
        List<Country> l_countryList = d_player.getCountriesOwned();
        Country l_minArmy = l_countryList.getFirst();

        for(Country l_country : l_countryList){
            if(l_minArmy.getArmies()<l_country.getArmies()){
                l_minArmy = l_country;
            }
        }
        return l_minArmy;
    }
}
