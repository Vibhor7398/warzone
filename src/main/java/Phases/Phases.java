package Phases;

import Controller.GameEngineController;
import GameEngine.GameEngine;
import Models.Command;
import Models.Strategy;
import Phases.GamePlay.MainPlay.MainPlay;

/**
 * The Phases class represents the various phases of the game.
 * It is an abstract class providing methods for executing commands in different phases.
 */
public abstract class Phases {
    GameEngine d_ge;

    /**
     * Displays the game map.
     *
     * @param p_command The command object.
     */
    public void showMap(Command p_command) {
        d_ge.getD_gc().executeShowMap();
    }

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    abstract public void editMap(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void saveMap(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void validateMap(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void editContinent(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void editCountry(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void editNeighbor(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void loadMap(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void assignPlayers(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void assignCountries(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void deploy(Command p_command);

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */
    abstract public void advance(Command p_command);
    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void bomb(Command p_command);

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void blockade(Command p_command);

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void airlift(Command p_command);

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void negotiate(Command p_command);

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void endGame(Command p_command);

    /**
     * Prints the error message
     * Since this is the exit phase, this method does nothing.
     *
     * @param p_command The command object.
     */

    abstract public void endTurn(Command p_command);


    /**
     * Transitions to the next phase.
     */
    abstract public void next();

    /**
     * Executes a series of commands.
     * This method iterates through the commands and executes them based on their type.
     *
     * @param p_command An array of Command objects to be executed.
     */
    public void execute(Command[] p_command) {
        for (Command l_command : p_command) {
            switch (l_command.getD_cmd()) {
                case "tournament":
                    d_ge.getD_phase().startTournament(l_command);
                    System.out.println("Tournament ended!");
                    System.exit(0);
                case "cpu-gameplay":
                    d_ge.getD_gc().executeCPUMove();
                    break;

                case "showmap":
                    d_ge.getD_phase().showMap(l_command);
                    break;


                case "editmap":
                    d_ge.getD_phase().editMap(l_command);
                    break;

                case "savemap":
                    d_ge.getD_phase().saveMap(l_command);
                    break;

                case "validatemap":
                    d_ge.getD_phase().validateMap(l_command);
                    break;

                case "editcontinent":
                    d_ge.getD_phase().editContinent(l_command);
                    break;

                case "editcountry":
                    d_ge.getD_phase().editCountry(l_command);
                    break;

                case "editneighbor":
                    d_ge.getD_phase().editNeighbor(l_command);
                    break;

                case "loadmap":
                    d_ge.getD_phase().loadMap(l_command);
                    break;

                case "assigncountries":
                    d_ge.getD_phase().assignCountries(l_command);
                    break;

                case "gameplayer":
                    d_ge.getD_phase().assignPlayers(l_command);
                    break;

                case "deploy":
                    d_ge.getD_phase().deploy(l_command);
                    break;

                case "advance":
                    d_ge.getD_phase().advance(l_command);
                    break;

                case "bomb":
                    d_ge.getD_phase().bomb(l_command);
                    break;

                case "blockade":
                    d_ge.getD_phase().blockade(l_command);
                    break;

                case "airlift":
                    d_ge.getD_phase().airlift(l_command);
                    break;

                case "negotiate":
                    d_ge.getD_phase().negotiate(l_command);
                    break;

                case "endturn":
                    d_ge.getD_phase().endTurn(l_command);
                    break;

                case "endgame":
                    d_ge.getD_phase().endGame(l_command);
                    break;

                case "next":
                    d_ge.getD_phase().next();
                    break;

                default:
                    printInvalidMessage();
                    break;
            }
        }
        d_ge.getD_gc().nextUserInput();


    }

    /**
     * Prints an invalid command message.
     * This method prints a message indicating that the command is invalid in the current state.
     */
    public void printInvalidMessage() {
        System.out.println("Invalid Command in the state " + this.getClass().getSimpleName());
    }

    /**
     * Prints an invalid command message.
     * This method prints a message indicating that the command is invalid in the current state.
     */
    public void startTournament(Command p_command) {
        d_ge.getD_gc().startTournament(p_command);
    }

    /**
     * Constructs a Phases object with the specified GameEngine.
     *
     * @param p_ge The GameEngine instance.
     */
    public Phases(GameEngine p_ge) {
        d_ge = p_ge;
    }

}
