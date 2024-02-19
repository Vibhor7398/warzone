package Models;

import Controller.MapsController;

public class Order{
    public void deployOrder(Player p_player, String p_countryName, int p_numberOfArmies){
        Country l_country = MapsController.getCountryByName(p_countryName);
        int l_previousArmies = 0;
        if (l_country != null) {
            l_previousArmies = l_country.getArmies();
        }
        int l_newArmies = p_numberOfArmies + l_previousArmies;
        if (l_country != null) {
            l_country.setArmies(l_newArmies);
        }
        System.out.println(p_numberOfArmies + " Army Deployment is successful on "+p_countryName+" by "+p_player.getName());
        System.out.println("Deployed armies on " + p_countryName + " is " + l_newArmies);
    }
}