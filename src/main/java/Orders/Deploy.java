/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Orders;

import Models.Country;
import Models.Player;
import java.util.ArrayList;
import Controller.GameEngineController;

/**
 * The Deploy class represents a deployment order in a game.
 * It allows a player to deploy a specified number of armies to a chosen country.
 */
public class Deploy implements Order {

    /** The player initiating the deployment. */
    Player d_player;

    /** The number of armies to be deployed. */
    int d_armyToBeDeployed;

    /** The country to which the armies are to be deployed. */
    Country d_country;

    /**
     * Constructs a Deployment object with the specified player, country, and number of armies to be deployed.
     *
     * @param p_player The player initiating the deployment.
     * @param p_country The country to which the armies are to be deployed.
     * @param p_armyToBeDeployed The number of armies to be deployed.
     */
    public Deploy(Player p_player, Country p_country, int p_armyToBeDeployed){
        this.d_armyToBeDeployed = p_armyToBeDeployed;
        this.d_player = p_player;
        this.d_country = p_country;
    }

    /**
     * Checks if the deployment order is valid.
     *
     * @return True if the deployment order is valid, false otherwise.
     */
    @Override
    public boolean isValid() {
        if(d_country==null || d_player == null){
            return false;
        }
        if (d_player.getArmies() >= d_armyToBeDeployed) {

            // Gather names of countries owned by the player
            ArrayList<String> l_countriesOwned = new ArrayList<>();
            for (Country l_country : d_player.getCountriesOwned()) {
                l_countriesOwned.add(l_country.getName());
            }

            // Check if the chosen country belongs to the player
            if (!l_countriesOwned.contains(d_country.getName())) {
                System.out.println("This country does not belong to you");
                GameEngineController.d_Log.notify("This country does not belong to "+d_player.getName(), "Game Play Phase");
                return false;
            }
            return true;

        } else {
            System.out.println("Not having enough armies");
            GameEngineController.d_Log.notify("Not having enough armies "+d_player.getName(), "Game Play Phase");
            System.out.println("Player "+d_player.getName()+" is having armies left = "+d_player.getArmies());
            return false;
        }
    }

    /**
     * Executes the deployment order if it is valid.
     */
    @Override
    public void execute() {
        if (isValid()) {
            int l_previousArmies = d_country.getArmies();
            int l_newArmies = d_armyToBeDeployed + l_previousArmies;
            d_country.setArmies(l_newArmies);
            print();
        } else {
            System.out.println("Invalid Order!");
        }
    }

    /**
     * Prints a message indicating the success of the deployment and the number of armies deployed.
     */
    @Override
    public void print() {
        System.out.println(d_armyToBeDeployed + " Army Deployment is successful on "+d_country.getName()+" by "+d_player.getName());
        GameEngineController.d_Log.notify(d_armyToBeDeployed + " Army Deployment is successful on "+d_country.getName()+" by "+d_player.getName(), "Game Play Phase");

        System.out.println("Deployed armies on " + d_country.getName() + " is " + d_country.getArmies());
        GameEngineController.d_Log.notify("Deployed armies on " + d_country.getName() + " is " + d_country.getArmies(), "Game Play Phase");

    }
}

