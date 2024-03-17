package Orders;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class TestAdvance {
    private Player player1;
    private Player player2;
    private Country sourceCountry;
    private Country targetCountryOwned;
    private Country targetCountryNotOwned;


    @Test
    public void testAdvanceMove() {
        // Setup - create actual objects rather than mocks
        Player player1 = new Player("Player1");
        GameEngineController gameC = new GameEngineController();
        gameC.executeLoadMap("brasil.map");
        gameC.executeAddGamePlayer("player2");

        Country sourceCountry = new Country(1, "Source", "Continent1", "0", "0");
        Country targetCountry = new Country(2, "Target", "Continent1", "1", "1");

        // Assume these methods exist to setup the test scenario
        sourceCountry.setArmies(10);
        targetCountry.setArmies(5);

        // Manually adding countries to player's control for the sake of the test
        player1.addCountryToCountriesOwned(sourceCountry);
        player1.addCountryToCountriesOwned(targetCountry);


        // Initialize the Advance order
        Advance advanceOrder = new Advance(player1, sourceCountry, targetCountry, 5);

        // Execute
        advanceOrder.execute();

        // Verify - Adjust assertions based on your actual methods and expected outcomes
        assertEquals("Source country should have 5 armies left.", 10, sourceCountry.getArmies());
        assertEquals("Target country should have 10 armies now.", 5, targetCountry.getArmies());
    }

    @Test
    public void testIsValid_SourceTerritoryNotOwnedByPlayer() {
        GameEngineController gameC = new GameEngineController();
        gameC.executeLoadMap("brasil.map");
        gameC.executeAddGamePlayer("player2");
        // Create a player
        Player player = new Player("Player1");
        Player player2 = new Player("Player2");
        // Create source and target territories
        Country sourceTerritory = new Country(1, "Source", "Continent1", "0", "0");
        Country targetTerritory = new Country(2, "Target", "Continent1", "1", "1");

        // Add source territory to player's list of owned territories
        player.addCountryToCountriesOwned(targetTerritory);

        // Create Advance object with source territory not owned by player
        Advance advance = new Advance(player, sourceTerritory, targetTerritory, 5);

        // Test isValid method
        assertFalse(advance.isValid());
    }

    @Test
    public void testPrint_UnsuccessfulAttack() {
        // Create a player
        Player player = new Player("Player1");
        //Player player2 = new Player("Player2");
        // Create source and target territories
        Country sourceTerritory = new Country(1, "Source", "Continent1", "0", "0");
        Country targetTerritory = new Country(2, "Target", "Continent1", "1", "1");

        // Add source and target territories to player's list of owned territories
        player.addCountryToCountriesOwned(sourceTerritory);
        player.addCountryToCountriesOwned(targetTerritory);

        // Set armies in source and target territories
        sourceTerritory.setArmies(5);
        targetTerritory.setArmies(10);

        // Create Advance object
        Advance advance = new Advance(player, sourceTerritory, targetTerritory, 3);

        // Set attack unsuccessful
        // advance.d_attack_successful = false;

        // Test print method
        advance.print();
    }


}