package Orders;

import Models.Country;
import Models.Player;

import java.util.Collection;

public class Bomb implements Order {

    Player d_player;
    Country d_country;
    public Bomb(Player p_player, Country p_country){
        this.d_player = p_player;
        this.d_country = p_country;
    }

    @Override
    public boolean isValid() {
        // Check if the player has the bomb card
        if(!d_player.getCardList().contains("Bomb")){
            System.out.println("player "+d_player.getName()+"doesn't have the bomb card! ");
            return false;
        }

        // Check if the country belongs to the player
        if (d_player.getCountriesOwned().contains(d_country)) {
            System.out.println("This country belongs to you");
            return false;
        }

        // Check if the country is a neighbor of any country owned by the player
        for (Country playerCountry : d_player.getCountriesOwned()) {
            if (playerCountry.getNeighbors().containsValue(d_country)) {
                return true;
            }
        }

        // If no neighboring country found, return false
        return false;
    }


    @Override
    public void execute() {
        if (isValid()) {
            d_country.setArmies(d_country.getArmies() / 2);
            d_player.removeCard("Bomb");
            print();
        } else {
            System.out.println("Invalid Order! ");
        }
    }

    @Override
    public void print() {
        System.out.println("Bombing is successful on " + d_country.getName() + " by " + d_player.getName());
        System.out.println("Armies on " + d_country.getName() + " after bombing is " + d_country.getArmies());
    }
}
