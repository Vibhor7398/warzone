package Services;

import static org.junit.Assert.*;
import Controller.GameEngineController;
import org.junit.Test;
import Models.Player;

public class TestReinforcement {
        @Test
        public void testAssignReinforcements() {
            // Create a list of players
            GameEngineController gameC = new GameEngineController();
            gameC.executeLoadMap("brasil.map");
            gameC.executeAddGamePlayer("player1");
            gameC.executeAddGamePlayer("player2");


            Player player1 = new Player("player1");
            Player player2 = new Player("player2");


            // Call the method to assign reinforcements
            Reinforcement.assignReinforcements(GameEngineController.d_Players);

            // Check if reinforcements are assigned correctly
            assertEquals(3, player1.getPlayerByName("player1").getArmies()); // Player 1 initially owns 0 countries, so should get base reinforcement of 3
            assertEquals(3, player2.getPlayerByName("player2").getArmies());; // Player 2 initially owns 0 countries, so should get base reinforcement of 3

            //call for assignCountries
            gameC.executeAssignCountries();

            // Check if reinforcements are assigned correctly after owning a country
            assertEquals(7, player1.getPlayerByName("player1").getArmies());
            assertEquals(7, player2.getPlayerByName("player2").getArmies());

        }
    }