/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
package Orders;

import Controller.GameEngineController;
import Models.Player;

/**
 * Represents a Diplomacy order in the game, allowing a player to negotiate peace with another player.
 * When a diplomacy order is executed, the players involved cannot attack each other for a certain period.
 */
public class Diplomacy implements Order{
    /**
     * The player issuing the diplomacy order.
     */
    Player d_player;

    /**
     * The player with whom diplomacy is being negotiated.
     */
    Player d_negotiatePlayer;

    /**
     * Constructs a Diplomacy order with the specified player and the player to negotiate with.
     *
     * @param p_player The player issuing the diplomacy order.
     * @param p_negotiatePlayer The player with whom diplomacy is being negotiated.
     */
    public Diplomacy(Player p_player, Player p_negotiatePlayer){
        this.d_player = p_player;
        this.d_negotiatePlayer = p_negotiatePlayer;
    }

     /**
     * Validates if the diplomacy order can be executed. Checks if the negotiate player exists, if the player exists,
     * if the player owns the diplomacy card, and if the player is not trying to negotiate with themselves.
     *
     * @return true if the order is valid; false otherwise.
     */
    @Override
    public boolean isValid() {
        if(d_negotiatePlayer==null || d_player == null){
            return false;
        }
        if(!d_player.getCardList().contains("Negotiate")){
            System.out.println("player " + d_player.getName() + "doesn't have the diplomacy card! ");
            GameEngineController.d_Log.notify("player " + d_player.getName() + "doesn't have the diplomacy card! ");
            return false;
        } else if (d_player.getName().equals(d_negotiatePlayer.getName())) {
            System.out.println("You cannot negotiate with yourself");
            GameEngineController.d_Log.notify("You cannot negotiate with yourself");
            return false;
        }
        return true;
    }

    /**
     * Executes the diplomacy order if it is valid, establishing a non-aggression pact between the issuing player
     * and the player they are negotiating with. Removes the negotiate card from the issuing player's card list.
     */
    @Override
    public void execute() {
        if (isValid()) {
            d_player.addNegotiatePlayer(d_negotiatePlayer);
            d_negotiatePlayer.addNegotiatePlayer(d_player);
            d_player.removeCard("Negotiate");
            print();
        } else {
            System.out.println("Invalid diplomacy Order! by "+d_player.getName());
            GameEngineController.d_Log.notify("Invalid Diplomacy Order! by "+d_player.getName());
        }
    }

    /**
     * Prints the result of the diplomacy order, indicating that diplomacy has been successfully applied
     * between the two players.
     */
    @Override
    public void print() {
        System.out.println("Diplomacy applied by "+d_player.getName()+" on the player "+d_negotiatePlayer.getName());
        GameEngineController.d_Log.notify("Diplomacy applied by "+d_player.getName()+" on the player "+d_negotiatePlayer.getName());
    }
}
