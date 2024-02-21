package Models;

/**
 *
 * 
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */

import Controller.MapsController;

/**
 * Represents an order for deploying armies to a country.
 */
public class Order{
    /**
     * Deploys a specified number of armies to a country owned by a player.
     *
     * @param p_player         The player who is deploying the armies.
     * @param p_countryName    The name of the country where the armies will be deployed.
     * @param p_numberOfArmies The number of armies to deploy.
     */
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