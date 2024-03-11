package Orders;

import Controller.MapsController;
import Models.Country;
import Models.Player;

import java.util.ArrayList;

public class Deploy implements Order{

    String d_countryName;
    Player d_player;
    int d_armyToBeDeployed;
    Country d_country;

    public Deploy(Player p_player, String p_countryName, int p_armyToBeDeployed){
        this.d_armyToBeDeployed = p_armyToBeDeployed;
        this.d_player = p_player;
        this.d_countryName = p_countryName;
        d_country = MapsController.getCountryByName(p_countryName);
    }


    @Override
    public boolean isValid() {
        if (d_player.getArmies() >= d_armyToBeDeployed) {

            ArrayList<String> l_countriesOwned = new ArrayList<>();
            for (Country l_country : d_player.getCountriesOwned()) {
                l_countriesOwned.add(l_country.getName());
            }
            if (!l_countriesOwned.contains(d_countryName)) {
                System.out.println(" This country does not belong to you ");
                return false;
            }
            return true;

        } else {
            System.out.println("Not having enough armies");
            System.out.println("Player "+d_player.getName()+" is having armies left = "+d_player.getArmies());
            return false;
        }
    }

    @Override
    public void execute() {
        if (isValid()) {
            int l_previousArmies = d_country.getArmies();
            int l_newArmies = d_armyToBeDeployed + l_previousArmies;
            d_country.setArmies(l_newArmies);
        } else {
            System.out.println("Invalid Order! ");
        }
        print();
    }

    @Override
    public void print() {
        System.out.println(d_armyToBeDeployed + " Army Deployment is successful on "+d_countryName+" by "+d_player.getName());
        System.out.println("Deployed armies on " + d_countryName + " is " + d_country.getArmies());
    }
}
