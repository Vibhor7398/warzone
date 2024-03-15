package Orders;

import Models.Country;
import Models.Player;

public class Bomb implements Order {

    Player d_player;
    Country d_country;
    public Bomb(Player p_player, Country p_country){
        this.d_player = p_player;
        this.d_country = p_country;
    }

    @Override
    public boolean isValid() {
        if (d_player.getCountriesOwned().contains(d_country)) {
            return true;
        } else {
            System.out.println("This country does not belong to you");
            return false;
        }
    }

    @Override
    public void execute() {
        if (isValid()) {
            d_country.setArmies(d_country.getArmies() / 2);
        } else {
            System.out.println("Invalid Order! ");
        }
        print();
    }

    @Override
    public void print() {
        System.out.println("Bombing is successful on " + d_country.getName() + " by " + d_player.getName());
        System.out.println("Armies on " + d_country.getName() + " after bombing is " + d_country.getArmies());
    }
}
