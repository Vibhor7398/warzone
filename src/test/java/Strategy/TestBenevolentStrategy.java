package Strategy;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import Models.Strategy;
import Orders.Advance;
import Orders.Deploy;
import Orders.Airlift;
import Orders.Order;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains unit tests for the BenevolentStrategy class.
 * It tests the creation of orders based on the benevolent strategy.
 */
public class TestBenevolentStrategy {
    private BenevolentStrategy d_benevolentStrategy;
    private Player d_testPlayer;
    private GameEngineController d_gc;

    /**
     * Set up the necessary objects for testing.
     */
    @Before
    public void setUp() {
        d_gc = new GameEngineController();
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
        l_country1.setArmies(1);
        l_country2.setArmies(2);
        Country l_country3 = new Country("Country3");
        l_country1.addNeighbor(l_country3);
        l_country2.addNeighbor(l_country3);

        d_benevolentStrategy = new BenevolentStrategy(d_testPlayer, l_countriesOwned);
    }

    /**
     * Test creating a deploy order to the weakest country.
     * Expected: Deploy order or null should be returned.
     */
    @Test
    public void testCreateOrder_DeployToWeakestCountry() {
        // Given
        d_testPlayer.setArmies(5);

        // When
        Order l_order = d_benevolentStrategy.createOrder();

        // Then
        assertTrue(l_order instanceof Advance ||  l_order == null);
        assertEquals("Country1", d_benevolentStrategy.toDefend().getName());
        assertEquals(1, d_benevolentStrategy.toDefend().getArmies());
    }

    /**
     * Test creating an airlift order if the card is available.
     * Expected: Airlift order should be returned.
     */
    @Test
    public void testCreateOrder_AirliftIfCardAvailable() {
        // Adjust initial conditions
        d_testPlayer.setArmies(0);
        d_testPlayer.addCard("Airlift");
        ArrayList<Country> l_countries = new ArrayList<>();
        l_countries = d_testPlayer.getCountriesOwned();
        int count =1;
        for(Country l_country : l_countries){
            l_country.setArmies(count);
            count++;
        }

        // Execute the strategy to create an order
        Order l_order = d_benevolentStrategy.createOrder();

        // Check if an Airlift order was created since the card is available
        assertTrue(l_order instanceof Airlift);
         assertEquals("Country2", d_benevolentStrategy.toMoveFrom().getName());
    }

    /**
     * Test creating an advance order if no airlift card is available.
     * Expected: Advance order should be returned.
     */
    @Test
    public void testCreateOrder_AdvanceIfNoAirliftCard() {
        // Make sure player does not have an Airlift card
        d_testPlayer.setArmies(0);
        d_testPlayer.getCountriesOwned().forEach(c -> c.setArmies(3));
        d_testPlayer.removeCard("Airlift"); // Assuming there's a method to remove cards

        // Strategy execution
        Order l_order = d_benevolentStrategy.createOrder();

        // Since no Airlift card is available, an Advance order should be created instead
        assertTrue(l_order instanceof Advance || l_order == null);
    }

}
