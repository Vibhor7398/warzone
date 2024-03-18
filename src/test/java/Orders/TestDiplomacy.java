/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 2.0
 */
package Orders;

import static org.junit.Assert.*;

import Models.Player;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains unit tests for the Diplomacy class.
 */
public class TestDiplomacy {

    private Player player1;
    private Player player2;

    /**
     * Sets up the test scenario before each test.
     * It initializes two players.
     */
    @Before
    public void setUp() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    /**
     * Tests the isValid method with valid conditions.
     * It checks if a Diplomacy order is valid when the negotiating player has a Negotiate card.
     */
    @Test
    public void testIsValidWithValidConditions() {
        // Add a Negotiate card to player1
        player1.addCard("Negotiate");
        // Create a Diplomacy order between player1 and player2
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        // Assert that the Diplomacy order is valid
        assertTrue(diplomacy.isValid());
    }

    /**
     * Tests the isValid method when the negotiating player does not have a Negotiate card.
     * It checks if a Diplomacy order is invalid when the negotiating player does not have a Negotiate card.
     */
    @Test
    public void testIsValidWithoutNegotiateCard() {
        // Create a Diplomacy order without adding a Negotiate card to player1
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        // Assert that the Diplomacy order is invalid
        assertFalse(diplomacy.isValid());
    }

    /**
     * Tests the isValid method when the negotiating player is the same as the negotiated player.
     * It checks if a Diplomacy order is invalid when the negotiating player is the same as the negotiated player.
     */
    @Test
    public void testIsValidWithSelfNegotiation() {
        // Add a Negotiate card to player1
        player1.addCard("Negotiate");
        // Create a Diplomacy order where player1 negotiates with themselves
        Diplomacy diplomacy = new Diplomacy(player1, player1);
        // Assert that the Diplomacy order is invalid
        assertFalse(diplomacy.isValid());
    }

    /**
     * Tests the isValid method when the negotiating player is null.
     * It checks if a Diplomacy order is invalid when the negotiating player is null.
     */
    @Test
    public void testIsValidWithNullPlayer() {
        // Add a Negotiate card to player1
        player1.addCard("Negotiate");
        // Create a Diplomacy order with null negotiating player
        Diplomacy diplomacy = new Diplomacy(player1, null);
        // Assert that the Diplomacy order is invalid
        assertFalse(diplomacy.isValid());
    }

    /**
     * Tests the isValid method when the negotiated player is null.
     * It checks if a Diplomacy order is invalid when the negotiated player is null.
     */
    @Test
    public void testIsValidWithNullNegotiatingPlayer() {
        // Add a Negotiate card to player1
        player1.addCard("Negotiate");
        // Create a Diplomacy order with null negotiated player
        Diplomacy diplomacy = new Diplomacy(null, player2);
        // Assert that the Diplomacy order is invalid
        assertFalse(diplomacy.isValid());
    }

    /**
     * Tests the execute method with valid conditions.
     * It verifies that executing a Diplomacy order updates the players' states correctly.
     */
    @Test
    public void testExecuteWithValidConditions() {
        // Add a Negotiate card to player1
        player1.addCard("Negotiate");
        // Create a Diplomacy order between player1 and player2
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        // Execute the Diplomacy order
        diplomacy.execute();
        // Assert that the Negotiate card is removed from player1's card list
        assertFalse(player1.getCardList().contains("Negotiate"));
        // Assert that player1 is added to player2's list of negotiated players
        assertTrue(player2.getNegotiatePlayers().contains(player1));
        // Assert that player2 is added to player1's list of negotiated players
        assertTrue(player1.getNegotiatePlayers().contains(player2));
    }

    /**
     * Tests the execute method without a Negotiate card.
     * It verifies that executing a Diplomacy order without a Negotiate card does not change the players' states.
     */
    @Test
    public void testExecuteWithoutNegotiateCard() {
        // Create a Diplomacy order without adding a Negotiate card to player1
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        // Execute the Diplomacy order
        diplomacy.execute();
        // Assert that player1 has no cards
        assertTrue(player1.getCardList().isEmpty());
        // Assert that neither player has negotiated players
        assertTrue(player1.getNegotiatePlayers().isEmpty());
        assertTrue(player2.getNegotiatePlayers().isEmpty());
    }

    /**
     * Tests the print method of Diplomacy.
     * It verifies that the print method executes without errors.
     */
    @Test
    public void testPrint() {
        // Add a Negotiate card to player1
        player1.addCard("Negotiate");
        // Create a Diplomacy order between player1 and player2
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        // Execute the Diplomacy order
        diplomacy.execute();
        // Call the print method, which should execute without errors
        diplomacy.print();
    }
}
