package Phases.GamePlay.Players;

import GameEngine.GameEngine;
import Models.Command;
import Phases.GamePlay.MainPlay.MainPlay;
import Phases.Phases;

/**
 * The Players class represents the phase where players are assigned to the game.
 * It extends the Phases class.
 */
public class Players extends Phases {
    private final GameEngine d_ge;
    /**
     * Constructs a Players object with the specified GameEngine.
     *
     * @param p_ge The GameEngine instance.
     */
    public Players(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
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
     * Loads the map.
     *
     * @param p_command The command object containing the map file path.
     */
    @Override
    public void loadMap(Command p_command) {
        d_ge.getD_gc().executeLoadMap(p_command.getArgs()[0]);
    }

    /**
     * Assigns players to the game.
     *
     * @param p_command The command object containing player assignment details.
     */
    @Override
    public void assignPlayers(Command p_command) {
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddGamePlayer(p_command.getArgs()[0]);
        }
        else{
            d_ge.getD_gc().executeRemoveGamePlayer(p_command.getArgs()[0]);
        }
    }

    /**
     * Assigns countries to players.
     * If successful, transitions to the next phase.
     *
     * @param p_command The command object.
     */
    @Override
    public void assignCountries(Command p_command) {
        boolean l_res = d_ge.getD_gc().executeAssignCountries();
        if(l_res){
            next();
        }
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
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    @Override
    public void endTurn(Command p_command) {
        printInvalidMessage();
    }

    /**
     * Transitions to the next phase.
     * This method sets the game phase to the main gameplay phase (MainPlay).
     */
    @Override
    public void next() {
        d_ge.setD_phase(new MainPlay(d_ge));
    }
}
