package Phases.MapEditor;
public interface MapEditorPhase {
    abstract public void showMap();
    abstract public void editMap();
    abstract public void saveMap();
    abstract public void validateMap();
    abstract public void editContinent();
    abstract public void editCountry();
    abstract public void editNeighbor();
}
