package Phases.MapEditor;

import GameEngine.GameEngine;
import Phases.GamePlay.Players.Players;
import Phases.Phases;

public class MapEditor extends Phases {
    private GameEngine d_ge;
    public MapEditor(GameEngine p_ge) {
        super(p_ge);
        d_ge = p_ge;
    }

    @Override
    public void showMap() {

    }

    @Override
    public void editMap() {
        System.out.println("EditMap");

    }

    @Override
    public void saveMap() {

    }

    @Override
    public void validateMap() {

    }

    @Override
    public void editContinent() {

    }

    @Override
    public void editCountry() {

    }

    @Override
    public void editNeighbor() {

    }

    @Override
    public void loadMap() {

        printInvalidMessage();
    }

    @Override
    public void assignPlayers() {
        printInvalidMessage();
    }

    @Override
    public void assignCountries() {
        printInvalidMessage();
    }

    @Override
    public void deploy() {
        printInvalidMessage();
    }

    @Override
    public void advance() {
        printInvalidMessage();
    }

    @Override
    public void bomb() {
        printInvalidMessage();
    }

    @Override
    public void blockade() {
        printInvalidMessage();
    }

    @Override
    public void airlift() {
        printInvalidMessage();
    }

    @Override
    public void negotiate() {
        printInvalidMessage();
    }

    @Override
    public void endGame() {

    }

    @Override
    public void next() {
        d_ge.setD_phase(new Players(d_ge));
    }
}
