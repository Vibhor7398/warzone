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

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    /**
     * Redirects System.out and System.err to ByteArrayOutputStreams to capture output for testing.
     */
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    /**
     * Test case to verify the behavior of issuing a deploy order for a human player.
     */
    @Test
    public void testIssueOrder_Human_Deploy() {
        GameEngineController gc = new GameEngineController();
        gc.executeLoadMap(AppConstants.MapsPath + "brasil.map");
        gc.executeAddGamePlayer("Human", Strategy.Human);
        gc.executeAddGamePlayer("Human1", Strategy.Human);
        Player player = GameEngineController.d_Players.getFirst();
        gc.executeAssignCountries();
        CommandValidator l_cmd = new CommandValidator();
        try {
            Command[] l_commands = l_cmd.validateCommand("deploy Parana 5");
            player.setOrder(l_commands[0]);

        } catch (InvalidCommandException e) {
            throw new RuntimeException(e);
        }
        player.issueOrder();
        assertTrue(outContent.toString().contains("Deploy order issued for Human"));
    }

    /**
     * Test case to verify the constructor of {@link Player} with a specified strategy.
     */
    @Test
    public void testPlayerConstructorWithStrategy() {
        Strategy strategy = Strategy.Aggressive;

        Player player = new Player("TestPlayer", strategy);

        assertEquals(strategy, player.get_playerStrategyType());
    }

}
