package Phases.GamePlay.Players;

import GameEngine.GameEngine;
import Models.Command;
import Phases.GamePlay.MainPlay.MainPlay;
import Phases.GamePlay.MainPlay.OrderCreationPhase;
import Phases.GamePlay.MainPlay.ReinforcementPhase;
import Phases.Phases;

public class Players extends Phases {
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
        boolean l_res = d_ge.getD_gc().executeAssignCountries();
        if(l_res){
            next();
        }
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
        d_ge.setD_phase(new OrderCreationPhase(d_ge));
    }
}
