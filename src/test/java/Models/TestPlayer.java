/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Models;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class provides unit tests for the Player class.
 * It covers test for failed deployment method.
 */
public class TestPlayer {
    private Player d_player = new Player("TestPlayer");

    /**
     * Tests if player can deploy more than allotted number of armies.
     * It adds a country to list of owned countries and sets number of armies to 3.
     * The test tries to deploy more armies than that of player.
     * The test checks if player is able to deploy armies or not.
     */
    @Test
    public void testCheckArmyExceeded(){
        d_player.addCountryToCountriesOwned(new Country(1, "country1", "1","0","0"));
        d_player.setArmies(3);
        boolean result = d_player.checkArmyExceeded(10,3);
        assertTrue(result);
    }
}
