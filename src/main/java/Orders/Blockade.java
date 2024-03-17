package Orders;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;

public class Blockade implements Order{
    Player d_player;
    Country d_country;
    public Blockade(Player p_player, Country p_country){
        this.d_player = p_player;
        this.d_country = p_country;
    }

    @Override
    public boolean isValid() {
        if(d_country==null || d_player == null){
            return false;
        }
        if(!d_player.getCardList().contains("Blockade")){
            System.out.println("player "+d_player.getName()+"doesn't have the blockade card! ");
            GameEngineController.d_Log.notify("player "+d_player.getName()+"doesn't have the blockade card! ");
            return false;
        } else if (d_player.getCountriesOwned().contains(d_country)) {
            return true;
        } else {
            System.out.println("This country does not belong to you");
            GameEngineController.d_Log.notify("This country does not belong to you");
            return false;
        }
    }

    @Override
    public void execute() {
        if (isValid()) {
            d_country.setArmies(d_country.getArmies() * 3);
            Player l_player = d_country.getOwner();
            l_player.removeCountryFromCountriesOwned(d_country);
            d_player.removeCard("Blockade");
            print();
        } else {
            System.out.println("Invalid Order! ");
            GameEngineController.d_Log.notify("Invalid Blockade Order! by "+d_player.getName());
        }
    }

    @Override
    public void print() {
        System.out.println("Blockade is successful on " + d_country.getName() + " by " + d_player.getName());
        GameEngineController.d_Log.notify("Blockade is successful on " + d_country.getName() + " by " + d_player.getName());
        System.out.println("Armies on " + d_country.getName() + " after blockade is " + d_country.getArmies());
        GameEngineController.d_Log.notify("Armies on " + d_country.getName() + " after blockade is " + d_country.getArmies());
    }
}
