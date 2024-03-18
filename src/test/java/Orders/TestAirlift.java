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
 * This class contains unit tests for the Airlift class.
 */
public class TestAirlift {
    Player d_player;
    Country d_sourceCountry;
    Country d_targetCountry;
    /**
     * Initializes player and countries before each test.
     */
    @Before
    public void setUp(){
        d_player = new Player("Abhi");
        d_sourceCountry = new Country("India");
        d_targetCountry = new Country("Canada");
        d_player.addCard("Airlift");
        d_player.addCountryToCountriesOwned(d_sourceCountry);
        d_player.addCountryToCountriesOwned(d_targetCountry);
    }
    /**
     * Tests the validity of the Airlift order.
     */
    @Test
    public void testIsValid() {
        d_sourceCountry.setArmies(10);
        int l_armyToBeAirlift = 5;

        Airlift l_airlift = new Airlift(d_player, d_sourceCountry, d_targetCountry, l_armyToBeAirlift);
        assertTrue(l_airlift.isValid());
    }
    /**
     * Tests the execution of the Airlift order.
     */
    @Test
    public void testExecute() {
        d_sourceCountry.setArmies(10);
        int l_armyToBeAirlift = 5;

        Airlift l_airlift = new Airlift(d_player, d_sourceCountry, d_targetCountry, l_armyToBeAirlift);
        l_airlift.execute();

        assertEquals(5, d_sourceCountry.getArmies());
        assertEquals(5, d_targetCountry.getArmies());
        assertFalse(d_player.getCardList().contains("Airlift"));
    }
    /**
     * Tests the printing functionality of the Airlift order.
     */
    @Test
    public void testPrint() {
        int l_armyToBeAirlift = 5;

        Airlift l_airlift = new Airlift(d_player, d_sourceCountry, d_targetCountry, l_armyToBeAirlift);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // Test print method
        l_airlift.print();

        System.setOut(System.out);

        // Assert the output
        String expectedOutput = "Abhi has successfully airlifted the number of armies: 5 from source country India to target country Canada.\n";
        assertEquals(expectedOutput.trim(), outContent.toString().trim());

    }
}