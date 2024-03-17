package Orders;

import Controller.GameEngineController;
import Models.Player;

public class Diplomacy implements Order{
    Player d_player;
    Player d_negotiatePlayer;
    public Diplomacy(Player p_player, Player p_negotiatePlayer){
        this.d_player = p_player;
        this.d_negotiatePlayer = p_negotiatePlayer;
    }
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

    @Override
    public void execute() {
        if (isValid()) {
            d_player.addNegotiatePlayer(d_negotiatePlayer);
            d_negotiatePlayer.addNegotiatePlayer(d_player);
            d_player.removeCard("Negotiate");
            print();
        } else {
            System.out.println("Invalid Order! ");
            GameEngineController.d_Log.notify("Invalid Diplomacy Order! by "+d_player.getName());
        }
    }

    @Override
    public void print() {
        System.out.println("Diplomacy applied by "+d_player.getName()+" on the player "+d_negotiatePlayer.getName());
        GameEngineController.d_Log.notify("Diplomacy applied by "+d_player.getName()+" on the player "+d_negotiatePlayer.getName());
    }
}
