package Phases.Exit;

import GameEngine.GameEngine;
import Models.Command;
import Phases.Phases;

public class Exit extends Phases {
    private final GameEngine d_ge;
    public Exit(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
        next();
    }

    @Override
    public void showMap(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void editMap(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void saveMap(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void validateMap(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void editContinent(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void editCountry(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void editNeighbor(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void loadMap(Command p_command) {

    }

    @Override
    public void assignPlayers(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void assignCountries(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void deploy(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void advance(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void bomb(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void blockade(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void airlift(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void negotiate(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void endGame(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void endTurn(Command p_command) {
        printInvalidMessage();
    }

    @Override
    public void next() {
        System.exit(0);
    }
}