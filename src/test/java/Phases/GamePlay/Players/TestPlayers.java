/**
 * The TestPlayers class is responsible for testing the functionality of the Players class.
 * It contains various test methods to verify different operations related to player management
 * and gameplay phases.
 *
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 *  *  * @version 2.0
 */
package Phases.GamePlay.Players;

import GameEngine.GameEngine;
import Models.Command;
import Models.Strategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Test class for Players class.
 */
public class TestPlayers {
    private GameEngine d_ge; // The instance of GameEngine
    private ByteArrayOutputStream outContent; // Output stream for testing console output
    private Players instance; // The instance of Players class under test
    private Command command; // The command object for testing

    /**
     * Setup method to initialize necessary objects before each test method execution.
     */
    @Before
    public void setUp()  {
        d_ge = new GameEngine();
        instance = new Players(d_ge);
        d_ge.setD_phase(instance);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Cleanup method to reset system output after each test method execution.
     */
    @After
    public void reset(){
        System.setOut(System.out);
    }

    /**
     * Test method to verify the assignment of players.
     * It checks whether a player is successfully added to the game.
     */
    @Test
    public void assignPlayers_valid(){
        command = new Command("gameplayer","-add",new String[]{"a"});
        instance.assignPlayers(command);
        assertNotEquals(-1,d_ge.getD_gc().doesPlayerExists("a"));
    }

    /**
     * Test method to verify the assignment of countries to players.
     * It ensures that players are assigned countries properly.
     */
    @Test
    public void assignCountries_valid(){
        command = new Command("assigncountries","",null);
        d_ge.getD_gc().executeLoadMap("brasil.map");
        d_ge.getD_gc().executeAddGamePlayer("a", Strategy.Human);
        d_ge.getD_gc().executeAddGamePlayer("b",Strategy.Human);
        instance.assignCountries(command);
        assertEquals("MainPlay", d_ge.getD_phase().getClass().getSimpleName());
    }

    /**
     * Test method to verify the behavior of endTurn method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void endTurn_invalid(){
        command = new Command("","",new String[0]);
        instance.endTurn(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of endGame method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void endGame_invalid(){
        command = new Command("","",new String[0]);
        instance.endGame(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of negotiate method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void negotiate_invalid(){
        command = new Command("","",new String[0]);
        instance.negotiate(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of airlift method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void airlift_invalid(){
        command = new Command("","",new String[0]);
        instance.airlift(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of blockade method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void blockade_invalid(){
        command = new Command("","",new String[0]);
        instance.blockade(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of bomb method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void bomb_invalid(){
        command = new Command("","",new String[0]);
        instance.bomb(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of advance method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void advance_invalid(){
        command = new Command("","",new String[0]);
        instance.advance(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Test method to verify the behavior of deploy method with invalid input.
     * It checks whether the appropriate error message is displayed.
     */
    @Test
    public void deploy_invalid(){
        command = new Command("","",new String[0]);
        instance.deploy(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }
}
