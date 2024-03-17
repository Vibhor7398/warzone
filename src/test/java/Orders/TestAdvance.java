package Orders;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TestAdvance {

    @Test
    public void testAdvanceMove() {
        // Setup - create actual objects rather than mocks
        Player l_player1 = new Player("Player1");
        GameEngineController l_gameC = new GameEngineController();
        l_gameC.executeLoadMap("brasil.map");
        l_gameC.executeAddGamePlayer("player2");

        Country l_sourceCountry = new Country(1, "Source", "Continent1", "0", "0");
        Country l_targetCountry = new Country(2, "Target", "Continent1", "1", "1");

        // Assume these methods exist to setup the test scenario
        l_sourceCountry.setArmies(10);
        l_targetCountry.setArmies(5);

        // Manually adding countries to player's control for the sake of the test
        l_player1.addCountryToCountriesOwned(l_sourceCountry);
        l_player1.addCountryToCountriesOwned(l_targetCountry);


        // Initialize the Advance order
        Advance l_advanceOrder = new Advance(l_player1, l_sourceCountry, l_targetCountry, 5);

        // Execute
        l_advanceOrder.execute();

        // Verify - Adjust assertions based on your actual methods and expected outcomes
        assertEquals("Source country should have 5 armies left.", 10, l_sourceCountry.getArmies());
        assertEquals("Target country should have 10 armies now.", 5, l_targetCountry.getArmies());
    }

    @Test
    public void testIsValid_SourceTerritoryNotOwnedByPlayer() {
        GameEngineController l_gameC = new GameEngineController();
        l_gameC.executeLoadMap("brasil.map");
        l_gameC.executeAddGamePlayer("player2");
        // Create a player
        Player l_player = new Player("Player1");
        // Create source and target territories
        Country l_sourceTerritory = new Country(1, "Source", "Continent1", "0", "0");
        Country l_targetTerritory = new Country(2, "Target", "Continent1", "1", "1");

        // Add source territory to player's list of owned territories
        l_player.addCountryToCountriesOwned(l_targetTerritory);

        // Create Advance object with source territory not owned by player
        Advance l_advance = new Advance(l_player, l_sourceTerritory, l_targetTerritory, 5);

        // Test isValid method
        assertFalse(l_advance.isValid());
    }

    @Test
    public void testPrint_UnsuccessfulAttack() {
        // Create a player
        Player l_player = new Player("Player1");
        // Create source and target territories
        Country l_sourceTerritory = new Country(1, "Source", "Continent1", "0", "0");
        Country l_targetTerritory = new Country(2, "Target", "Continent1", "1", "1");

        // Add source and target territories to player's list of owned territories
        l_player.addCountryToCountriesOwned(l_sourceTerritory);
        l_player.addCountryToCountriesOwned(l_targetTerritory);

        // Set armies in source and target territories
        l_sourceTerritory.setArmies(5);
        l_targetTerritory.setArmies(10);

        // Create Advance object
        Advance l_advance = new Advance(l_player, l_sourceTerritory, l_targetTerritory, 3);

        // Test print method
        l_advance.print();
    }
}