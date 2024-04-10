/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Orders;

import Models.Country;
import Models.Player;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the Bomb class.
 */
public class TestBomb {

    private Player d_player;
    private Country d_playerCountry;
    private Country d_neighboringCountry;
    private Country d_nonNeighboringCountry;
    /**
     * Sets up the test scenario before each test.
     */
    @Before
    public void setUp() {
        d_player = new Player("TestPlayer");
        d_player.addCard("Bomb");
        d_player.setArmies(10);
        d_playerCountry = new Country("PlayerCountry");
        d_neighboringCountry = new Country("NeighboringCountry");
        d_nonNeighboringCountry = new Country("NonNeighboringCountry");

        // Set up player's owned countries and neighboring countries
        d_player.addCountryToCountriesOwned(d_playerCountry);
        d_playerCountry.addNeighbor(d_neighboringCountry);
    }
    /**
     * Tests the validity of the Bomb order when the country belongs to the player.
     */

    @Test
    public void testIsValidWhenCountryBelongsToPlayer() {
        Bomb l_bomb = new Bomb(d_player, d_playerCountry);

        assertFalse(l_bomb.isValid());
    }
    /**
     * Tests the validity of the Bomb order when the country is a neighbor of an owned country.
     */
    @Test
    public void testIsValidWhenCountryIsNeighborOfOwnedCountry() {
        Bomb l_bomb = new Bomb(d_player, d_neighboringCountry);

        assertTrue(l_bomb.isValid());
    }
    /**
     * Tests the validity of the Bomb order when the country is not a neighbor of any owned country.
     */
    @Test
    public void testIsValidWhenCountryIsNotNeighborOfOwnedCountry() {
        Bomb l_bomb = new Bomb(d_player, d_nonNeighboringCountry);

        assertFalse(l_bomb.isValid());
    }
    /**
     * Tests the execution of the Bomb order when it is valid.
     */
    @Test
    public void testExecuteWhenValid() {
        Bomb l_bomb = new Bomb(d_player, d_neighboringCountry);
        d_neighboringCountry.setArmies(10);

        l_bomb.execute();

        assertEquals(5, d_neighboringCountry.getArmies());
    }

    /**
     * Tests the execution of the Bomb order when it is invalid.
     */
    @Test
    public void testExecuteWhenInvalid() {
        Bomb l_bomb = new Bomb(d_player, d_nonNeighboringCountry);
        d_nonNeighboringCountry.setArmies(10);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // Test print method
        l_bomb.execute();
        //l_bomb.print();

        System.setOut(System.out);

        // Assert the output
        String expectedOutput = "Invalid Bomb Order! by TestPlayer";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());
    }
}
