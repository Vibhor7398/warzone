package Models;

import Models.Country;
import Models.Player;

import java.util.ArrayList;

public class Order{


    Player d_player;
    int d_numberOfArmies;
    String d_countryName;
    Country d_country;

    public void deployOrder(Player p_player, String p_countryName, int p_numberOfArmies){
        if (d_player.getArmies() >= d_numberOfArmies) {
            ArrayList<String> countriesOwned = new ArrayList<>();
            for (Country country : d_player.getCountriesOwned()) {
                countriesOwned.add(country.getName());
            }

            if (!countriesOwned.contains(d_countryName)) {
                System.out.println("This country is not in the list ....... Please try with your country");
            }
        } else {
            System.out.println("No of Army is less that what you are trying to deploy");
            System.out.println("your Army count is: " + d_player.getArmies());
        }

        System.out.println("Army Deployment for \nPlayer : " + d_player.getName() + " at Country : " + d_countryName
                + " and the count of Army is: " + d_numberOfArmies);

        int remainingArmies = d_player.getArmies() - d_numberOfArmies;
        d_player.setArmies(remainingArmies);
/*
        Country country = d_country.getCountryByName(d_countryName);
        int previousArmies = country.getArmies();
        int newArmies = d_numberOfArmies + previousArmies;
        country.setArmies(newArmies);
*/
        System.out.println("Army Deployment is successfull.\n");
    }
}