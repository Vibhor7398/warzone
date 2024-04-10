package Strategy;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import Models.Strategy;
import Orders.Advance;
import Orders.Order;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * This class contains unit tests for the AggressiveStrategy class.
 * It tests the creation of orders based on the aggressive strategy.
 */
public class TestAggressiveStrategy {
    private AggressiveStrategy d_aggressiveStrategy;
    private Player d_testPlayer;
    GameEngineController d_gc = new GameEngineController();
    /**
     * Set up the necessary objects for testing.
     */
    @Before
    public void setUp() {
        d_gc.executeAddGamePlayer("TestPlayer", Strategy.Human);
        d_testPlayer = GameEngineController.d_Players.getFirst();

        List<Country> l_countriesOwned = new ArrayList<>();
        Country l_country1 = new Country("Country1");
        l_countriesOwned.add(l_country1);
        Country l_country2 = new Country("Country2");
        l_countriesOwned.add(l_country2);
        d_testPlayer.addCountryToCountriesOwned(l_country1);
        d_testPlayer.addCountryToCountriesOwned(l_country2);
        l_country1.addNeighbor(l_country2);
        Country l_country3 = new Country("Country3");
        l_country1.addNeighbor(l_country3);
        l_country2.addNeighbor(l_country3);

        d_aggressiveStrategy = new AggressiveStrategy(d_testPlayer, l_countriesOwned);
    }

    /**
     * Test creating a deploy order when armies are available.
     * Expected: Deploy order or null should be returned.
     */
    @Test
    public void testCreateOrder_DeployWhenArmiesAvailable() {
        d_testPlayer.setArmies(5);
        Order l_order = d_aggressiveStrategy.createOrder();
        assertTrue(l_order instanceof Advance || l_order == null);
    }

    /**
     * Test creating an advance order when no armies are available.
     * Expected: Advance order should be returned.
     */
    @Test
    public void testCreateOrder_AdvanceWhenNoArmiesAvailable() {
        d_testPlayer.setArmies(0);

        List<Country> l_countriesOwned = d_testPlayer.getCountriesOwned();
        for (Country l_country : l_countriesOwned) {
            l_country.setArmies(3);
        }

        Order l_order = d_aggressiveStrategy.createOrder();
        assertEquals(Advance.class, l_order.getClass());
    }
}
