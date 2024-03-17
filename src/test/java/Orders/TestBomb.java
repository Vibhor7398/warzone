package Orders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import Models.Country;
import Models.Player;

public class TestBomb {

    private Player d_player;
    private Country d_playerCountry;
    private Country d_neighboringCountry;
    private Country d_nonNeighboringCountry;

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

    @Test
    public void testIsValidWhenCountryBelongsToPlayer() {
        Bomb l_bomb = new Bomb(d_player, d_playerCountry);

        assertFalse(l_bomb.isValid());
    }

    @Test
    public void testIsValidWhenCountryIsNeighborOfOwnedCountry() {
        Bomb l_bomb = new Bomb(d_player, d_neighboringCountry);

        assertTrue(l_bomb.isValid());
    }

    @Test
    public void testIsValidWhenCountryIsNotNeighborOfOwnedCountry() {
        Bomb l_bomb = new Bomb(d_player, d_nonNeighboringCountry);

        assertFalse(l_bomb.isValid());
    }

    @Test
    public void testExecuteWhenValid() {
        Bomb l_bomb = new Bomb(d_player, d_neighboringCountry);
        d_neighboringCountry.setArmies(10);

        l_bomb.execute();

        assertEquals(5, d_neighboringCountry.getArmies());
    }

    @Test
    public void testExecuteWhenInvalid() {
        Bomb l_bomb = new Bomb(d_player, d_nonNeighboringCountry);
        d_nonNeighboringCountry.setArmies(10);
        l_bomb.execute();
    }
}
