package Phases.MapEditor;

import GameEngine.GameEngine;
import Models.Command;
import Phases.GamePlay.Players.Players;
import Phases.Phases;

/**
 * The MapEditor class represents the phase where map editing operations are performed.
 * It extends the Phases class.
 */
public class MapEditor extends Phases {
    private final GameEngine d_ge;
    /**
     * Constructs a MapEditor object with the specified GameEngine.
     *
     * @param p_ge The GameEngine instance.
     */
    public MapEditor(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
    }

    /**
     * Edits the map.
     *
     * @param p_command The command object containing edit map details.
     */
    @Override
    public void editMap(Command p_command) {
        d_ge.getD_gc().executeEditMap(p_command.getArgs()[0]);
    }

    /**
     * Saves the map.
     *
     * @param p_command The command object containing save map details.
     */
    @Override
    public void saveMap(Command p_command) {
        d_ge.getD_gc().executeSaveMap(p_command.getArgs()[0]);
    }

    /**
     * Validates the map.
     * This method executes map validation.
     *
     * @param p_command The command object.
     */
    @Override
    public void validateMap(Command p_command) {
        d_ge.getD_gc().executeValidateMap();
    }

    /**
     * Edits continents on the map.
     * This method either adds or removes a continent based on the command received.
     *
     * @param p_command The command object containing continent edit details.
     */
    @Override
    public void editContinent(Command p_command) {
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddContinent(p_command.getArgs()[0], Integer.parseInt(p_command.getArgs()[1]));
        }
        else{
            d_ge.getD_gc().executeRemoveContinent(p_command.getArgs()[0]);
        }
    }

    /**
     * Edits countries on the map.
     * This method either adds or removes a country based on the command received.
     *
     * @param p_command The command object containing country edit details.
     */
    @Override
    public void editCountry(Command p_command) {
        System.out.println("EditCountry"+p_command.toString());
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddCountry(p_command.getArgs()[0], p_command.getArgs()[1]);
        }
        else{
            d_ge.getD_gc().executeRemoveCountry(p_command.getArgs()[0]);
        }
    }


    /**
     * Edits neighbors of countries on the map.
     * This method either adds or removes a neighbor for a country based on the command received.
     *
     * @param p_command The command object containing neighbor edit details.
     */
    @Override
    public void editNeighbor(Command p_command) {
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddNeighbor(p_command.getArgs()[0], p_command.getArgs()[1]);
        }
        else{
            d_ge.getD_gc().executeRemoveNeighbor(p_command.getArgs()[0], p_command.getArgs()[1]);
        }
    }

    /**
     * Loads the map.
     * This method loads a map and transitions to the next phase (player assignment).
     *
     * @param p_command The command object containing the map file path.
     */
    @Override
    public void loadMap(Command p_command) {
        d_ge.getD_gc().executeLoadMap(p_command.getArgs()[0]);
        next();
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
     * Transitions to the player assignment phase.
     * This method sets the game phase to the player assignment phase (Players).
     */
    @Override
    public void next() {
        d_ge.setD_phase(new Players(d_ge));
    }
}
