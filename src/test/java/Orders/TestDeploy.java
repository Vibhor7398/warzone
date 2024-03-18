package Orders;//package Orders;

import Models.Country;
import Models.Player;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class contains unit tests for the Deploy class.
 */
public class TestDeploy {
    Player d_player;
    Country d_country;
    /**
     * Sets up the test scenario before each test.
     */
    @Before
    public void setUp(){
        d_player = new Player("Abhi");
        d_country = new Country("India");
        d_player.addCountryToCountriesOwned(d_country);
    }
    /**
     * Tests the validity of the Deploy order with enough armies and owned country.
     */
    @Test
    public void testIsValidWithEnoughArmiesAndOwnedCountry() {
        d_player.setArmies(10);
        Deploy l_deploy = new Deploy(d_player, d_country, 5);
        boolean l_result = l_deploy.isValid();
        assertTrue(l_result);
    }
    /**
     * Tests the validity of the Deploy order with not enough armies.
     */
    @Test
    public void testIsValidWithNotEnoughArmies() {
        d_player.setArmies(3);
        Deploy l_deploy = new Deploy(d_player, d_country, 5);
        boolean l_result = l_deploy.isValid();
        assertFalse(l_result);
    }
    /**
     * Tests the validity of the Deploy order with country not owned.
     */
    @Test
    public void testIsValidWithCountryNotOwned() {
        d_player.setArmies(10);
        d_country = new Country("Canada");
        Deploy l_deploy = new Deploy(d_player, d_country, 5);
        boolean l_result = l_deploy.isValid();
        assertFalse(l_result);
    }
    /**
     * Tests the execution of the Deploy order with a valid order.
     */
    @Test
    public void testExecuteWithValidOrder() {
        d_player.setArmies(10);
        d_country.setArmies(2);
        Deploy l_deploy = new Deploy(d_player, d_country, 5);
        l_deploy.execute();
        assertEquals(7, d_country.getArmies());
    }
    /**
     * Tests the execution of the Deploy order with an invalid order.
     */
    @Test
    public void testExecuteWithInvalidOrder() {
        d_player.setArmies(3);
        Deploy l_deploy = new Deploy(d_player, d_country, 5);
        l_deploy.execute();
        assertEquals(3, d_player.getArmies());
    }
}
