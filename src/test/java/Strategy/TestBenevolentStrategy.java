package Strategy;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
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

public class TestBenevolentStrategy {
    private BenevolentStrategy d_benevolentStrategy;
    private Player d_testPlayer;
    private GameEngineController d_gc;

    @Before
    public void setUp() {
        d_gc = new GameEngineController();
        d_gc.executeAddGamePlayer("TestPlayer");
        d_testPlayer = GameEngineController.d_Players.getFirst();

        List<Country> l_countriesOwned = new ArrayList<>();
        Country l_country1 = new Country("Country1");
        l_countriesOwned.add(l_country1);
        Country l_country2 = new Country("Country2");
        l_countriesOwned.add(l_country2);
        d_testPlayer.addCountryToCountriesOwned(l_country1);
        d_testPlayer.addCountryToCountriesOwned(l_country2);
        l_country1.addNeighbor(l_country2);

        d_benevolentStrategy = new BenevolentStrategy(d_testPlayer, l_countriesOwned);
    }

    @Test
    public void testCreateOrder_DeployToWeakestCountry() {
        // Given
        d_testPlayer.setArmies(5);

        // When
        Order l_order = d_benevolentStrategy.createOrder();

        // Then
        assertTrue(l_order instanceof Deploy);
        assertEquals("Country1", d_benevolentStrategy.toDefend());
        assertEquals(5, d_benevolentStrategy.toDefend().getArmies());
    }

    @Test
    public void testCreateOrder_AirliftIfCardAvailable() {
        // Adjust initial conditions
        d_testPlayer.setArmies(0);
        d_testPlayer.getCountriesOwned().forEach(c -> c.setArmies(3));
        d_testPlayer.addCard("Airlift");

        // Execute the strategy to create an order
        Order l_order = d_benevolentStrategy.createOrder();

        // Check if an Airlift order was created since the card is available
        assertTrue(l_order instanceof Airlift);
        // assertEquals("Country2", ((Airlift) l_order).getSourceCountry().getName());
        // assertEquals("Country1", ((Airlift) l_order).getTargetCountry().getName());
    }

    @Test
    public void testCreateOrder_AdvanceIfNoAirliftCard() {
        // Make sure player does not have an Airlift card
        d_testPlayer.setArmies(0);
        d_testPlayer.getCountriesOwned().forEach(c -> c.setArmies(3));
        d_testPlayer.removeCard("Airlift"); // Assuming there's a method to remove cards

        // Strategy execution
        Order l_order = d_benevolentStrategy.createOrder();

        // Since no Airlift card is available, an Advance order should be created instead
        assertTrue(l_order instanceof Advance);
    }

}
