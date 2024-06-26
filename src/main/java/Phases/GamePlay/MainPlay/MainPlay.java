package Phases.GamePlay.MainPlay;
import Controller.GameEngineController;
import GameEngine.GameEngine;
import Models.Command;
import Phases.Exit.Exit;
import Phases.Phases;

/**
 * The MainPlay class represents the main gameplay phase of the game.
 * In this phase, players perform various actions such as deploying armies, issuing orders, and advancing to the next turn.
 * It extends the Phases class.
 */
public class MainPlay extends Phases {
    private final GameEngine d_ge;
    /**
     * Constructs a MainPlay object with the specified GameEngine.
     *
     * @param p_ge The GameEngine instance.
     */
    public MainPlay(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
        GameEngineController.d_Log.notify("Currently in phase: MainPlay");
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void editMap(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void saveMap(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void validateMap(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void editContinent(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void editCountry(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void editNeighbor(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void loadMap(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void assignPlayers(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void assignCountries(Command p_command) {
        printInvalidMessage();
    }


    /**
     * Sets orders for deploying armies.
     *
     * @param p_command The command object containing deploy orders.
     */
    @Override
    public void deploy(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for advancing armies.
     *
     * @param p_command The command object containing advance orders.
     */
    @Override
    public void advance(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for bombing a territory.
     *
     * @param p_command The command object containing bomb orders.
     */
    @Override
    public void bomb(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for blockading a territory.
     *
     * @param p_command The command object containing blockade orders.
     */
    @Override
    public void blockade(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for airlifting armies.
     *
     * @param p_command The command object containing airlift orders.
     */
    @Override
    public void airlift(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for negotiating with other players.
     *
     * @param p_command The command object containing negotiate orders.
     */
    @Override
    public void negotiate(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for ending the game.
     *
     * @param p_command The command object containing end game orders.
     */
    @Override
    public void endGame(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Sets orders for ending the current turn.
     *
     * @param p_command The command object containing end turn orders.
     */
    @Override
    public void endTurn(Command p_command) {
        d_ge.getD_gc().setOrders(p_command);
    }

    /**
     * Transition to the exit phase.
     * This method sets the game phase to the exit phase.
     */
    @Override
    public void next() {
        d_ge.setD_phase(new Exit(d_ge));
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     */
    /**
     * Saves the current state of the game with the specified file name.
     *
     * @param p_command The command object containing the file name.
     */
    @Override
    public void saveGame(Command p_command) {
        // Check if the save operation was successful
        if(d_ge.getD_gc().executeSaveGame(p_command.getArgs()[0])){
            // Print success message
            System.out.println("Game " + p_command.getArgs()[0] + " saved successfully!");
            // Notify the log
            GameEngineController.d_Log.notify("Game " + p_command.getArgs()[0] + " saved successfully!");
        }
        else{
            // Notify the log about the failure
            GameEngineController.d_Log.notify("Game " + p_command.getArgs()[0] + " failed to save!");
        }
    }

    /**
     * Loads a previously saved game state from the specified file name.
     *
     * @param p_command The command object containing the file name.
     */
    @Override
    public void loadGame(Command p_command) {
        // Check if the load operation was successful
        if(d_ge.getD_gc().executeLoadGame(p_command.getArgs()[0])){
            // Print success message
            System.out.println("Game " + p_command.getArgs()[0] + " loaded successfully!");
            // Notify the log
            GameEngineController.d_Log.notify("Game " + p_command.getArgs()[0] + " loaded successfully!");
        }
        else{
            // Notify the log about the failure
            GameEngineController.d_Log.notify("Game " + p_command.getArgs()[0] + " failed to load!");
        }
    }


}
