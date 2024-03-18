package Phases.GamePlay.Players;

import GameEngine.GameEngine;
import Models.Command;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TestPlayers {
    private GameEngine d_ge;
    private ByteArrayOutputStream outContent;
    private Players instance;
    private Command command;

    @Before
    public void setUp()  {
        d_ge = new GameEngine();
        instance = new Players(d_ge);
        d_ge.setD_phase(instance);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void reset(){
        System.setOut(System.out);
    }


    @Test
    public void assignPlayers_valid(){
        command = new Command("gameplayer","-add",new String[]{"a"});
        instance.assignPlayers(command);
        assertNotEquals(-1,d_ge.getD_gc().doesPlayerExists("a"));
    }

    @Test
    public void assignCountries_valid(){
        command = new Command("assigncountries","",null);
        d_ge.getD_gc().executeLoadMap("brasil.map");
        d_ge.getD_gc().executeAddGamePlayer("a");
        d_ge.getD_gc().executeAddGamePlayer("b");
        instance.assignCountries(command);
        assertEquals("MainPlay", d_ge.getD_phase().getClass().getSimpleName());
    }

    @Test
    public void endTurn_invalid(){
        command = new Command("","",new String[0]);
        instance.endTurn(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void endGame_invalid(){
        command = new Command("","",new String[0]);
        instance.endGame(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void negotiate_invalid(){
        command = new Command("","",new String[0]);
        instance.negotiate(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void airlift_invalid(){
        command = new Command("","",new String[0]);
        instance.airlift(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void blockade_invalid(){
        command = new Command("","",new String[0]);
        instance.blockade(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void bomb_invalid(){
        command = new Command("","",new String[0]);
        instance.bomb(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void advance_invalid(){
        command = new Command("","",new String[0]);
        instance.advance(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }

    @Test
    public void deploy_invalid(){
        command = new Command("","",new String[0]);
        instance.deploy(command);
        String expectedOutput = "Invalid Command in the state Players\n";
        assertEquals(outContent.toString().trim(), expectedOutput.trim());
    }


}
