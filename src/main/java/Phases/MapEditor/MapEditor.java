package Phases.MapEditor;

import GameEngine.GameEngine;
import Models.Command;
import Phases.GamePlay.Players.Players;
import Phases.Phases;

public class MapEditor extends Phases {

    public MapEditor() {
    }

    @Override
    public void editMap(Command p_command) {
        d_ge.getD_gc().executeEditMap(p_command.getArgs()[0]);
    }

    @Override
    public void saveMap(Command p_command) {
        d_ge.getD_gc().executeSaveMap(p_command.getArgs()[0]);
    }

    @Override
    public void validateMap(Command p_command) {
        d_ge.getD_gc().executeValidateMap();
    }

    @Override
    public void editContinent(Command p_command) {
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddContinent(p_command.getArgs()[0], Integer.parseInt(p_command.getArgs()[1]));
        }
        else{
            d_ge.getD_gc().executeRemoveContinent(p_command.getArgs()[0]);
        }
    }

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

    @Override
    public void editNeighbor(Command p_command) {
        if(p_command.getD_subCmd().equals("-add")){
            d_ge.getD_gc().executeAddNeighbor(p_command.getArgs()[0], p_command.getArgs()[1]);
        }
        else{
            d_ge.getD_gc().executeRemoveNeighbor(p_command.getArgs()[0], p_command.getArgs()[1]);
        }
    }

    @Override
    public void loadMap(Command p_command) {
        d_ge.getD_gc().executeLoadMap(p_command.getArgs()[0]);
        next();
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
        d_ge.setD_phase(new Players());
    }
}
