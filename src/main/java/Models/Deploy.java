package Models;

import Interface.Order;

import java.util.ArrayList;
import java.util.HashMap;

public class Deploy implements Order {

    Player d_player;
    Country d_country;
    String d_countryName;
    //String d_countryId;
    int d_numberOfArmies;
    private HashMap<Integer, Country> d_countryList;

    public Deploy(Player p_player, String p_countryName, int p_numberOfArmies) {
        d_player = p_player;
        d_countryName = p_countryName;
        d_numberOfArmies = p_numberOfArmies;
        //d_country = new Country(p_countryId, getCountryName(p_countryId));
    }



    @Override
    public boolean isValid() {
        if (d_player.getArmies() >= d_numberOfArmies) {
            ArrayList<String> countriesOwned = new ArrayList<>();
            for (Country country : d_player.getCountriesOwned()) {
                countriesOwned.add(country.getName());
            }

            if (!countriesOwned.contains(d_countryName)) {
                System.out.println("This country is not in the list ....... Please try with your country");
                return false;
            }

            return true;

        } else {
            System.out.println("No of Army is less that what you are trying to deploy");
            System.out.println("your Army count is: " + d_player.getArmies());
            return false;
        }
        return false;
    }


    @Override
    public void executeOrder() {
        printOrder();
        if(isValid()){
            int remainingArmies = d_player.getArmies() - d_numberOfArmies;
            d_player.setArmies(remainingArmies);

            Country country = d_country.getCountryName(d_countryName);
            int previousArmies = country.getArmies();
            int newArmies = d_numberOfArmies + previousArmies;
            country.setArmies(newArmies);

            System.out.println("Army Deployment is successfull.\n");
        } else {
            System.out.println("Invalid Order.\n");
        }
    }

    @Override
    public void printOrder() {
        System.out.println("Army Deployment for \nPlayer : " + d_player.getName() + " at Country : " + d_countryName
                + " and the count of Army is: " + d_numberOfArmies);
    }
}
