package Phases.GamePlay.MainPlay;

import GameEngine.GameEngine;
import Models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class TestMainPlay {
    private GameEngine d_ge;
    private ByteArrayOutputStream outContent;
    private MainPlay instance;
    private Command command;

    @Before
    public void setUp()  {
        d_ge = new GameEngine();
        instance = new MainPlay(d_ge);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void reset(){
        System.setOut(System.out);
    }

    @Test
    public void editContinent_invalid(){
        command = new Command("","",new String[0]);
        instance.editContinent(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void saveMap_invalid(){
        command = new Command("","",new String[0]);
        instance.saveMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void validateMap_invalid(){
        command = new Command("","",new String[0]);
        instance.validateMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void editCountry_invalid(){
        command = new Command("","",new String[0]);
        instance.editCountry(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void editNeighbor_invalid(){
        command = new Command("","",new String[0]);
        instance.editNeighbor(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void loadMap_invalid(){
        command = new Command("","",new String[0]);
        instance.loadMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void assignPlayers_invalid(){
        command = new Command("","",new String[0]);
        instance.assignPlayers(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }


    @Test
    public void editMap_invalid(){
        command = new Command("","",new String[0]);
        instance.editMap(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void assignCountries_invalid(){
        command = new Command("","",new String[0]);
        instance.assignCountries(command);
        String expectedOutput = "Invalid Command in the state MainPlay\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }
}
