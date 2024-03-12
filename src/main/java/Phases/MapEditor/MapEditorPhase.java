package Phases.MapEditor;
public interface MapEditorPhase {
    abstract public void showMap();
    abstract public void editMap();
    abstract public void saveMap();
    abstract public void validateMap();
    abstract public void editContinent();
    abstract public void editCountry();
    abstract public void editNeighbor();
//    abstract public void loadMap();
//    abstract public void assignPlayers();
//    abstract public void assignCountries();
//    abstract public void deploy();
//    abstract public void advance();
//    abstract public void bomb();
//    abstract public void blockade();
//    abstract public void airlift();
//    abstract public void negotiate();
}
