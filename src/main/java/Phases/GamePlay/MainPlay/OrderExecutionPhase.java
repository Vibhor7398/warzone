package Phases.GamePlay.MainPlay;

import GameEngine.GameEngine;
import Models.Command;
import Phases.Phases;

public class OrderExecutionPhase extends Phases {
    public void executeOrders(){
        d_ge.getD_gc().executeAllOrders();
        next();
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
        printInvalidMessage();
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
        d_ge.setD_phase(new ReinforcementPhase());
    }
}
