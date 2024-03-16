package Phases.MapEditor;

import GameEngine.GameEngine;
import Models.Command;
import Phases.GamePlay.Players.Players;
import Phases.Phases;

public class MapEditor extends Phases {
    private final GameEngine d_ge;
    public MapEditor(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
    }

    @Override
    public void showMap(Command p_command) {
        System.out.println("Showmap"+p_command.toString());
    }

    @Override
    public void editMap(Command p_command) {
        System.out.println("EditMap"+p_command.toString());

    }

    @Override
    public void saveMap(Command p_command) {
        System.out.println("Savemap"+p_command.toString());

    }

    @Override
    public void validateMap(Command p_command) {
        System.out.println("ValidateMap"+p_command.toString());
    }

    @Override
    public void editContinent(Command p_command) {
        System.out.println("EditContinet"+p_command.toString());

    }

    @Override
    public void editCountry(Command p_command) {
        System.out.println("EditCountry"+p_command.toString());

    }

    @Override
    public void editNeighbor(Command p_command) {
        System.out.println("EditNeighbor"+p_command.toString());


    }

    @Override
    public void loadMap(Command p_command) {
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

    }

    @Override
    public void next() {
        d_ge.setD_phase(new Players(d_ge));
    }
}
