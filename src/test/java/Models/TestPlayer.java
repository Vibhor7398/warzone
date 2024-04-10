package Models;

import static org.junit.Assert.*;
import Constants.AppConstants;
import Controller.GameEngineController;
import Services.CommandValidator;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import Exception.InvalidCommandException;

/**
 * Test class for testing the functionality of the {@link Player} class.
 */
public class TestPlayer {

    private final ByteArrayOutputStream d_outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream d_errContent = new ByteArrayOutputStream();


    /**
     * Redirects System.out and System.err to ByteArrayOutputStreams to capture output for testing.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(d_outContent));
        System.setErr(new PrintStream(d_errContent));
    }

    /**
     * Test case to verify the behavior of issuing a deploy order for a human player.
     */
    @Test
    public void testIssueOrder_Human_Deploy() {
        GameEngineController l_gc = new GameEngineController();
        l_gc.executeLoadMap(AppConstants.MapsPath + "brasil.map");
        l_gc.executeAddGamePlayer("Human", Strategy.Human);
        l_gc.executeAddGamePlayer("Human1", Strategy.Human);
        Player l_player = GameEngineController.d_Players.getFirst();
        l_gc.executeAssignCountries();
        CommandValidator l_cmd = new CommandValidator();
        try {
            Command[] l_commands = l_cmd.validateCommand("deploy Parana 5");
            l_player.setOrder(l_commands[0]);

        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }
        l_player.issueOrder();
        assertTrue(d_outContent.toString().contains("Deploy order issued for Human"));
    }

    /**
     * Test case to verify the constructor of {@link Player} with a specified strategy.
     */
    @Test
    public void testPlayerConstructorWithStrategy() {
        Strategy l_strategy = Strategy.Aggressive;

        Player l_player = new Player("TestPlayer", l_strategy);

        assertEquals(l_strategy, l_player.get_playerStrategyType());
    }

}
