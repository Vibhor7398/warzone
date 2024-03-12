package Orders;//package Orders;

import Models.Country;
import Models.Player;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestDeploy {

    @Test
    public void testIsValidWithEnoughArmiesAndOwnedCountry() {
        Player l_player = new Player("Abhi");
        l_player.setArmies(10);
        Country l_country = new Country("India");
        l_player.addCountryToCountriesOwned(l_country);
        Deploy l_deploy = new Deploy(l_player, l_country, 5);

        boolean l_result = l_deploy.isValid();

        assertTrue(l_result);
    }

    @Test
    public void testIsValidWithNotEnoughArmies() {
        Player l_player = new Player("Abhi");
        l_player.setArmies(3);
        Country l_country = new Country("India");
        Deploy l_deploy = new Deploy(l_player, l_country, 5);

        boolean l_result = l_deploy.isValid();

        assertFalse(l_result);
    }

    @Test
    public void testIsValidWithCountryNotOwned() {
        Player l_player = new Player("Abhi");
        l_player.setArmies(10);
        Country l_country = new Country("India");
        Deploy l_deploy = new Deploy(l_player, l_country, 5);

        boolean l_result = l_deploy.isValid();

        assertFalse(l_result);
    }

    @Test
    public void testExecuteWithValidOrder() {
        Player l_player = new Player("Abhi");
        l_player.setArmies(10);
        Country l_country = new Country("India");
        l_country.setArmies(2);
        l_player.addCountryToCountriesOwned(l_country);
        Deploy l_deploy = new Deploy(l_player, l_country, 5);

        l_deploy.execute();

        assertEquals(7, l_country.getArmies());
    }

    @Test
    public void testExecuteWithInvalidOrder() {
        Player l_player = new Player("Abhi");
        l_player.setArmies(3);
        Country l_country = new Country("India");
        Deploy l_deploy = new Deploy(l_player, l_country, 5);

        l_deploy.execute();

        assertEquals(3, l_player.getArmies());
    }
}
