package Models;

import Models.Country;
import Models.Player;

import java.util.ArrayList;

public class Order{
    Country d_country;


    public void deployOrder(Player p_player, String p_countryName, int p_numberOfArmies){
        if (p_player.getArmies() >= p_numberOfArmies) {
            ArrayList<String> countriesOwned = new ArrayList<>();
            for (Country country : p_player.getCountriesOwned()) {
                countriesOwned.add(country.getName());
            }

            if (!countriesOwned.contains(p_countryName)) {
                System.out.println("This country is not in the list ....... Please try with your country");
            }
        } else {
            System.out.println("No of Army is less that what you are trying to deploy");
            System.out.println("your Army count is: " + p_player.getArmies());
        }

        System.out.println("Army Deployment for \nPlayer : " + p_player.getName() + " at Country : " + p_countryName
                + " and the count of Army is: " + p_numberOfArmies);

        int remainingArmies = p_player.getArmies() - p_numberOfArmies;
        p_player.setArmies(remainingArmies);
/*
        Country country = d_country.getCountryByName(p_countryName);
        int previousArmies = country.getArmies();
        int newArmies = p_numberOfArmies + previousArmies;
        country.setArmies(newArmies);
*/
        System.out.println("Army Deployment is successfull.\n");
    }
}