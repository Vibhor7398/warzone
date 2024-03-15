package Orders;

import Models.Country;
import Models.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAirlift {
    Player d_player;
    Country d_sourceCountry;
    Country d_targetCountry;

    @Before
    public void setUp(){
        d_player = new Player("Abhi");
        d_sourceCountry = new Country("India");
        d_targetCountry = new Country("Canada");
        d_player.addCard("Airlift");
        d_player.addCountryToCountriesOwned(d_sourceCountry);
        d_player.addCountryToCountriesOwned(d_targetCountry);
    }
    @Test
    public void testIsValid() {
        d_sourceCountry.setArmies(10);
        int l_armyToBeAirlift = 5;

        Airlift l_airlift = new Airlift(d_player, d_sourceCountry, d_targetCountry, l_armyToBeAirlift);
        assertTrue(l_airlift.isValid());
    }

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

    @Test
    public void testPrint() {
        int l_armyToBeAirlift = 5;

        Airlift l_airlift = new Airlift(d_player, d_sourceCountry, d_targetCountry, l_armyToBeAirlift);
        l_airlift.print();
    }
}