/**
 * This package contains classes related to the Map Editor phase of the game.
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Phases.MapEditor;

import GameEngine.GameEngine;
import Models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * This class contains unit tests for the MapEditor class.
 * It tests various methods in the MapEditor class for their behavior when invalid commands are passed.
 */
public class TestMapEditor {
    private GameEngine d_ge;
    private ByteArrayOutputStream outContent;
    private MapEditor instance;
    private Command command;

    /**
     * Sets up the test environment before each test method.
     * It initializes the GameEngine, MapEditor, and redirects standard output to a ByteArrayOutputStream.
     */
    @Before
    public void setUp()  {
        d_ge = new GameEngine();
        instance = new MapEditor(d_ge);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets the standard output after each test method.
     * It sets the standard output back to System.out.
     */
    @After
    public void reset(){
        System.setOut(System.out);
    }

    /**
     * Tests the assignPlayers method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void assignPlayers_invalid(){
        command = new Command("","",new String[0]);
        instance.assignPlayers(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the endTurn method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void endTurn_invalid(){
        command = new Command("","",new String[0]);
        instance.endTurn(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the endGame method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void endGame_invalid(){
        command = new Command("","",new String[0]);
        instance.endGame(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the negotiate method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void negotiate_invalid(){
        command = new Command("","",new String[0]);
        instance.negotiate(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the airlift method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void airlift_invalid(){
        command = new Command("","",new String[0]);
        instance.airlift(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the blockade method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void blockade_invalid(){
        command = new Command("","",new String[0]);
        instance.blockade(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the bomb method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void bomb_invalid(){
        command = new Command("","",new String[0]);
        instance.bomb(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the advance method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void advance_invalid(){
        command = new Command("","",new String[0]);
        instance.advance(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the deploy method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void deploy_invalid(){
        command = new Command("","",new String[0]);
        instance.deploy(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the assignCountries method with an invalid command.
     * It verifies that the correct output is displayed when an invalid command is passed.
     */
    @Test
    public void assignCountries_invalid(){
        command = new Command("","",new String[0]);
        instance.assignCountries(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }
}
