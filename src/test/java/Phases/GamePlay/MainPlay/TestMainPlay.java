/**
 * @author Vibhor Gulati
 * @author Apoorva Sharma
 * @author Saphal Ghimire
 * @author Inderjeet Singh Chauhan
 * @author Mohammad Zaid Shaikh
 * @version 2.0
 */
package Phases.GamePlay.MainPlay;

import GameEngine.GameEngine;
import Models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 *  This class contains unit tests for the MainPlay class.
 *  It tests various methods in the MainPlay class for their behavior when invalid commands are passed.
 *
 */
public class TestMainPlay {

    private GameEngine d_ge;
    private ByteArrayOutputStream outContent;
    private MainPlay instance;
    private Command command;

    /**
     * Sets up the test environment before each test method is executed.
     */
    @Before
    public void setUp()  {
        d_ge = new GameEngine();
        instance = new MainPlay(d_ge);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Resets the test environment after each test method is executed.
     */
    @After
    public void reset(){
        System.setOut(System.out);
    }

    /**
     * Tests the editContinent method with invalid input.
     */
    @Test
    public void editContinent_invalid(){
        command = new Command("","",new String[0]);
        instance.editContinent(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the saveMap method with invalid input.
     */
    @Test
    public void saveMap_invalid(){
        command = new Command("","",new String[0]);
        instance.saveMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the validateMap method with invalid input.
     */
    @Test
    public void validateMap_invalid(){
        command = new Command("","",new String[0]);
        instance.validateMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the editCountry method with invalid input.
     */
    @Test
    public void editCountry_invalid(){
        command = new Command("","",new String[0]);
        instance.editCountry(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the editNeighbor method with invalid input.
     */
    @Test
    public void editNeighbor_invalid(){
        command = new Command("","",new String[0]);
        instance.editNeighbor(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the loadMap method with invalid input.
     */
    @Test
    public void loadMap_invalid(){
        command = new Command("","",new String[0]);
        instance.loadMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the assignPlayers method with invalid input.
     */
    @Test
    public void assignPlayers_invalid(){
        command = new Command("","",new String[0]);
        instance.assignPlayers(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the editMap method with invalid input.
     */
    @Test
    public void editMap_invalid(){
        command = new Command("","",new String[0]);
        instance.editMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    /**
     * Tests the assignCountries method with invalid input.
     */
    @Test
    public void assignCountries_invalid(){
        command = new Command("","",new String[0]);
        instance.assignCountries(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }
}
