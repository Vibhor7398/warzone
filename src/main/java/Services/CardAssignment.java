package Services;

import java.util.Random;

public class CardAssignment {
    public static String getCard(){
        String[] l_cards = {"Bomb", "Blockade", "Airlift", "Negotiate"};
        Random l_rand = new Random();
        int l_randomIndex = l_rand.nextInt(l_cards.length);
        return l_cards[l_randomIndex];
    }
}
