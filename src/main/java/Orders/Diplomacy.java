package Orders;

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
        if(!d_player.getCardList().contains("Diplomacy")){
            System.out.println("player " + d_player.getName() + "doesn't have the diplomacy card! ");
            return false;
        } else if (d_player.getName().equals(d_negotiatePlayer.getName())) {
            System.out.println("You cannot negotiate with yourself");
            return false;
        }
        return true;
    }

    @Override
    public void execute() {
        if (isValid()) {
            d_player.addNegotiatePlayer(d_negotiatePlayer);
            d_negotiatePlayer.addNegotiatePlayer(d_player);
            d_player.removeCard("Diplomacy");
            print();
        } else {
            System.out.println("Invalid Order! ");
        }
    }

    @Override
    public void print() {

    }
}
