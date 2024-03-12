package Phases;

import GameEngine.GameEngine;

public abstract class Phases {
    GameEngine d_ge;
    abstract public void showMap();
    abstract public void editMap();
    abstract public void saveMap();
    abstract public void validateMap();
    abstract public void editContinent();
    abstract public void editCountry();
    abstract public void editNeighbor();
    abstract public void loadMap();
    abstract public void assignPlayers();
    abstract public void assignCountries();
    abstract public void deploy();
    abstract public void advance();
    abstract public void bomb();
    abstract public void blockade();
    abstract public void airlift();
    abstract public void negotiate();
    abstract public void endGame();
    abstract public void next();
    public void printInvalidMessage(){
        System.out.println("Invalid Command in the state "+this.getClass().getSimpleName());
    }
    public Phases(GameEngine p_ge){
        d_ge = p_ge;
    }

}
