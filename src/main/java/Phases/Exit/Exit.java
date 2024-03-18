package Phases.Exit;

import GameEngine.GameEngine;
import Models.Command;
import Phases.Phases;
/**
 * The Exit class represents the exit phase of the game.
 * This phase allows the player to exit the game.
 * It extends the Phases class.
 */
public class Exit extends Phases {
    private final GameEngine d_ge;
    /**
     * Constructs an Exit object with the specified GameEngine.
     *
     * @param p_ge The GameEngine instance.
     */
    public Exit(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
        next();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void showMap(Command p_command) {
        printInvalidMessage();
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
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void deploy(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void advance(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void bomb(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void blockade(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void airlift(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void negotiate(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void endGame(Command p_command) {
        printInvalidMessage();
    }


    /**
     * Ends the current turn.
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void endTurn(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     */
    @Override
    public void next() {
        System.exit(0);
    }
}
