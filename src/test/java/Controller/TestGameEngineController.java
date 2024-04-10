/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Controller;

import Models.Strategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains JUnit test cases for the {@link GameEngineController} class.
 */
public class TestGameEngineController {
    private GameEngineController d_gameEngineController;

    /**
     * Sets up the test environment before each test method execution.
     */
    @Before
    public void setUp() {
        d_gameEngineController = new GameEngineController();
    }

    /**
     * Cleans up the test environment after each test method execution.
     */
    @After
    public void tearDown() {
        d_gameEngineController = null;
    }

    /**
     * It verifies that a player is successfully added to the game.
     */
    @Test
    public void testAddGamePlayer() {
        d_gameEngineController.executeAddGamePlayer("Player1", Strategy.Human);
        assertEquals(1, d_gameEngineController.d_Players.size());
    }

    /**
     * Tests the {@link GameEngineController#executeRemoveGamePlayer(String)} method.
     * It verifies that a player is successfully removed from the game.
     */
    @Test
    public void testRemoveGamePlayer() {
        d_gameEngineController.executeAddGamePlayer("Player1",Strategy.Human);
        d_gameEngineController.executeRemoveGamePlayer("Player1");
        assertEquals(0, d_gameEngineController.d_Players.size());
    }
}
