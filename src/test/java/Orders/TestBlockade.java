/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Orders;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * This class contains unit tests for the Blockade class.
 */
public class TestBlockade {
    private Player d_player;
    private Country d_country;

    /**
     * Sets up the test scenario before each test.
     * It initializes a test player, adds them to the game engine controller's list of players,
     * and assigns a country to the test player.
     */
    @Before
    public void setUp() {
        // Initialize a game engine controller
        GameEngineController l_gameEngineController = new GameEngineController();
        // Add a test player to the game
        l_gameEngineController.executeAddGamePlayer("TestPlayer");
        // Initialize the test player
        d_player = new Player("TestPlayer");
        // Add the test player to the list of players in the game engine controller
        l_gameEngineController.d_Players.add(d_player);
        // Initialize a test country
        d_country = new Country("TestCountry");
        // Add the test country to the list of countries owned by the test player
        d_player.addCountryToCountriesOwned(d_country);
    }


    /**
     * Tests the isValid method when the player parameter is null.
     */
    @Test
    public void testIsValidWithNullPlayer() {
        // Create a blockade with null player
        Blockade l_blockade = new Blockade(null, d_country);
        // Assert that isValid returns false
        assertFalse(l_blockade.isValid());
    }

    /**
     * Tests the isValid method when the country parameter is null.
     */
    @Test
    public void testIsValidWithNullCountry() {
        // Create a blockade with null country
        Blockade l_blockade = new Blockade(d_player, null);
        // Assert that isValid returns false
        assertFalse(l_blockade.isValid());
    }

    /**
     * Tests the isValid method when the player has no Blockade card.
     */
    @Test
    public void testIsValidWithNoBlockadeCard() {
        // Create a blockade with a player who has no Blockade card
        Blockade l_blockade = new Blockade(d_player, d_country);
        // Assert that isValid returns false
        assertFalse(l_blockade.isValid());
    }

    /**
     * Tests the isValid method with valid inputs.
     */
    @Test
    public void testIsValidWithValidInputs() {
        // Add a Blockade card to the test player's card list
        d_player.addCard("Blockade");
        // Create a blockade with valid inputs
        Blockade l_blockade = new Blockade(d_player, d_country);
        // Assert that isValid returns true
        assertTrue(l_blockade.isValid());
    }

    /**
     * Tests the execute method of Blockade.
     * It verifies that executing a blockade removes armies from the country and removes the country from the player's list of owned countries.
     */
    @Test
    public void testExecute() {
        // Add a Blockade card to the test player's card list
        d_player.addCard("Blockade");
        // Create a blockade
        Blockade l_blockade = new Blockade(d_player, d_country);
        // Execute the blockade
        l_blockade.execute();
        // Assert that the country is removed from the player's list of owned countries
        assertEquals(0, d_player.getCountriesOwned().size());
        // Assert that the country has no armies left
        assertEquals(0, d_country.getArmies());
    }

    /**
     * Tests the execute method of Blockade when the order is invalid.
     * It verifies that executing a blockade without a Blockade card does not change the state.
     */
    @Test
    public void testExecuteWithInvalidOrder() {
        // Create a blockade without adding a Blockade card to the player
        Blockade l_blockade = new Blockade(d_player, d_country);
        // Execute the blockade, which should not change the state
        l_blockade.execute();
        // Assert that the player still owns the country
        assertEquals(1, d_player.getCountriesOwned().size());
        // Assert that the country still has no armies
        assertEquals(0, d_country.getArmies());
    }

    /**
     * Tests the print method of Blockade.
     * It verifies that the print method executes without errors.
     */
    @Test
    public void testPrint() {
        // Add a Blockade card to the test player's card list
        d_player.addCard("Blockade");
        // Create a blockade
        Blockade l_blockade = new Blockade(d_player, d_country);
        // Execute the blockade
        l_blockade.execute();
        // Call the print method, which should execute without errors
        l_blockade.print();
    }
}