package Services;

import java.util.Random;
/**
 * This class provides methods for assigning random cards to players.
 */
public class CardAssignment {
    /**
     * Retrieves a random card from the available card pool.
     *
     * @return A randomly selected card.
     */
    public static String getCard(){
        String[] l_cards = {"Bomb", "Blockade", "Airlift", "Negotiate"};
        Random l_rand = new Random();
        int l_randomIndex = l_rand.nextInt(l_cards.length);
        return l_cards[l_randomIndex];
    }
}
