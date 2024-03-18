package Orders;

import static org.junit.Assert.*;

import Models.Player;
import org.junit.Before;
import org.junit.Test;

public class TestDiplomacy {

    private Player player1;
    private Player player2;

    @Before
    public void setUp() {
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
    }

    @Test
    public void testIsValidWithValidConditions() {
        player1.addCard("Negotiate");
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        assertTrue(diplomacy.isValid());
    }

    @Test
    public void testIsValidWithoutNegotiateCard() {
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        assertFalse(diplomacy.isValid());
    }

    @Test
    public void testIsValidWithSelfNegotiation() {
        player1.addCard("Negotiate");
        Diplomacy diplomacy = new Diplomacy(player1, player1);
        assertFalse(diplomacy.isValid());
    }

    @Test
    public void testIsValidWithNullPlayer() {
        player1.addCard("Negotiate");
        Diplomacy diplomacy = new Diplomacy(player1, null);
        assertFalse(diplomacy.isValid());
    }

    @Test
    public void testIsValidWithNullNegotiatingPlayer() {
        player1.addCard("Negotiate");
        Diplomacy diplomacy = new Diplomacy(null, player2);
        assertFalse(diplomacy.isValid());
    }

    @Test
    public void testExecuteWithValidConditions() {
        player1.addCard("Negotiate");
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        diplomacy.execute();
        assertFalse(player1.getCardList().contains("Negotiate"));
        assertTrue(player1.getNegotiatePlayers().contains(player2));
        assertTrue(player2.getNegotiatePlayers().contains(player1));
    }

    @Test
    public void testExecuteWithoutNegotiateCard() {
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        diplomacy.execute();
        assertTrue(player1.getCardList().isEmpty());
        assertTrue(player1.getNegotiatePlayers().isEmpty());
        assertTrue(player2.getNegotiatePlayers().isEmpty());
    }

    @Test
    public void testPrint() {
        player1.addCard("Negotiate");
        Diplomacy diplomacy = new Diplomacy(player1, player2);
        diplomacy.execute();
        diplomacy.print();
    }
}
