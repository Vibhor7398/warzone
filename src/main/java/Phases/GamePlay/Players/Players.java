package Phases.GamePlay.Players;

import GameEngine.GameEngine;
import Models.Command;
import Phases.GamePlay.MainPlay.MainPlay;
import Phases.Phases;

public class Players extends Phases {
    private final GameEngine d_ge;
    public Players(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
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
        d_ge.getD_gc().executeLoadMap(p_command.getArgs()[0]);
    }

    @Override
    public void assignPlayers(Command p_command) {
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddGamePlayer(p_command.getArgs()[0]);
        }
        else{
            d_ge.getD_gc().executeRemoveGamePlayer(p_command.getArgs()[0]);
        }
    }

    @Override
    public void assignCountries(Command p_command) {
        d_ge.getD_gc().executeAssignCountries();
        next();
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
        d_ge.setD_phase(new MainPlay(d_ge));
    }
}
