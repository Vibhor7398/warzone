package Phases.MapEditor;

import GameEngine.GameEngine;
import Models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestMapEditor {
    private GameEngine d_ge;
    private ByteArrayOutputStream outContent;
    private MapEditor instance;
    private Command command;

    @Before
    public void setUp()  {
        d_ge = new GameEngine();
        instance = new MapEditor(d_ge);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @After
    public void reset(){
        System.setOut(System.out);
    }

    @Test
    public void assignPlayers_invalid(){
        command = new Command("","",new String[0]);
        instance.assignPlayers(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void endTurn_invalid(){
        command = new Command("","",new String[0]);
        instance.endTurn(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void endGame_invalid(){
        command = new Command("","",new String[0]);
        instance.endGame(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void negotiate_invalid(){
        command = new Command("","",new String[0]);
        instance.negotiate(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void airlift_invalid(){
        command = new Command("","",new String[0]);
        instance.airlift(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void blockade_invalid(){
        command = new Command("","",new String[0]);
        instance.blockade(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void bomb_invalid(){
        command = new Command("","",new String[0]);
        instance.bomb(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void advance_invalid(){
        command = new Command("","",new String[0]);
        instance.advance(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void deploy_invalid(){
        command = new Command("","",new String[0]);
        instance.deploy(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void assignCountries_invalid(){
        command = new Command("","",new String[0]);
        instance.assignCountries(command);
        String expectedOutput = "Invalid Command in the state MapEditor\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }
}