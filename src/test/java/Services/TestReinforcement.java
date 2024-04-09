/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Services;

import Controller.GameEngineController;
import Models.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains unit tests for the reinforcement phase of the game.
 * It tests the functionality of assigning reinforcements to players based on their owned countries.
 */
public class TestReinforcement {

    /**
     * This method tests the functionality of assigning reinforcements to players.
     * It verifies that players receive the correct number of reinforcements based on their owned countries.
     */
    @Test
    public void testAssignReinforcements() {
        // Create a list of players
        GameEngineController l_gameC = new GameEngineController();
        l_gameC.executeLoadMap("brasil.map");
        l_gameC.executeAddGamePlayer("player1");
        l_gameC.executeAddGamePlayer("player2");

        Player l_player1 = new Player("player1");
        Player l_player2 = new Player("player2");

        // Call the method to assign reinforcements
        Reinforcement.assignReinforcements(GameEngineController.d_Players);

        // Check if reinforcements are assigned correctly
        assertEquals(3, l_player1.getPlayerByName("player1").getArmies()); // Player 1 initially owns 0 countries, so should get base reinforcement of 3
        assertEquals(3, l_player2.getPlayerByName("player2").getArmies()); // Player 2 initially owns 0 countries, so should get base reinforcement of 3

        //call for assignCountries
        l_gameC.executeAssignCountries();

        // Check if reinforcements are assigned correctly after owning a country
        assertEquals(7, l_player1.getPlayerByName("player1").getArmies());
        assertEquals(7, l_player2.getPlayerByName("player2").getArmies());
    }
}
