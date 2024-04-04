package Strategy;

import Models.Country;
import Models.Player;
import Orders.Order;
import java.util.List;

public class CheaterStrategy extends PlayerStrategy{
    public CheaterStrategy(Player p_player, List<Country> p_country) {
        super(p_player, p_country);
    }

    @Override
    public Order createOrder() {
        for(Country l_cheaterCountry : d_player.getCountriesOwned()){
            for(Country l_cheaterNeighbour : l_cheaterCountry.getNeighbors().values()){
                if(l_cheaterNeighbour.getOwner()!=d_player){
                    Player l_player = l_cheaterNeighbour.getOwner();
                    l_player.removeCountryFromCountriesOwned(l_cheaterNeighbour);
                    d_player.addCountryToCountriesOwned(l_cheaterCountry);
                }
            }
        }

        for(Country l_cheaterCountry : d_player.getCountriesOwned()){
            for(Country l_cheaterNeighbour : l_cheaterCountry.getNeighbors().values()){
                if(l_cheaterNeighbour.getOwner()!=d_player){
                    l_cheaterNeighbour.setArmies(l_cheaterNeighbour.getArmies() * 2);
                    break;
                }
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
        return null;
    }

    @Override
    protected Country toDefend() {
        return null;
    }
}
