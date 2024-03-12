package Orders;

import Models.Country;
import Models.Player;

public class Airlift implements Order{

    Country d_sourceCountry;
    Country d_targetCountry;
    int d_armyToBeAirlift;
    Player d_player;

    public Airlift(Player p_player, Country p_sourceCountry, Country p_targetCountry, int p_armyToBeAirlift){
        this.d_armyToBeAirlift = p_armyToBeAirlift;
        this.d_sourceCountry = p_sourceCountry;
        this.d_targetCountry = p_targetCountry;
        this.d_player = p_player;
    }
    @Override
    public boolean isValid() {
        if(!d_player.getCardList().contains("Airlift")){
            System.out.println("player "+d_player.getName()+"doesn't have the airlift card! ");
            return false;
        }
        if(!d_player.getCountriesOwned().contains(d_targetCountry)){
            System.out.println("Player "+d_player.getName()+" is not owing the target country "+d_targetCountry.getName());
            return false;
        }
        if(!d_player.getCountriesOwned().contains(d_sourceCountry)){
            System.out.println("Player "+d_player.getName()+" is not owing the source country "+d_sourceCountry.getName());
            return false;
        }
        if(d_sourceCountry.getName().equals(d_targetCountry.getName())){
            System.out.println("Source and Target country must not be different ");
            return false;
        }
        if(d_sourceCountry.getArmies() < d_armyToBeAirlift){
            System.out.println(d_player.getName()+ " is not having suffient army on country "+d_sourceCountry.getName()+" to airlift to target country "+d_targetCountry.getName());
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        if(isValid()){
            d_sourceCountry.setArmies(d_sourceCountry.getArmies()-d_armyToBeAirlift);
            System.out.println("Armies remaining on source country "+d_sourceCountry.getName()+" after airlift is "+d_sourceCountry.getArmies());
            d_targetCountry.setArmies(d_targetCountry.getArmies()+d_armyToBeAirlift);
            System.out.println("Armies available on target country "+d_targetCountry.getName()+" after airlift is "+d_targetCountry.getArmies());
            d_player.removeCard("Airlift");
            print();
        } else {
            System.out.println("Invalid Order! ");
        }
    }

    @Override
    public void print() {
        System.out.println(d_player.getName()+ " have successfully airlift the number of armies : "+d_armyToBeAirlift+" from source country "+d_sourceCountry.getName()+" to target country "+d_targetCountry.getName()+".");
    }
}
