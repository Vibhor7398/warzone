package Phases.GamePlay.Players;

import GameEngine.GameEngine;
import Phases.Phases;

public class Players extends Phases {

    public Players(GameEngine p_ge) {
        super(p_ge);
    }

    @Override
    public void showMap() {
        printInvalidMessage();
    }

    @Override
    public void editMap() {
        printInvalidMessage();
    }

    @Override
    public void saveMap() {
        printInvalidMessage();
    }

    @Override
    public void validateMap() {
        printInvalidMessage();
    }

    @Override
    public void editContinent() {
        printInvalidMessage();
    }

    @Override
    public void editCountry() {
        printInvalidMessage();
    }

    @Override
    public void editNeighbor() {
        printInvalidMessage();
    }

    @Override
    public void loadMap() {

    }

    @Override
    public void assignPlayers() {
        System.out.println("assignPlayers");
    }

    @Override
    public void assignCountries() {

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

    }
}
