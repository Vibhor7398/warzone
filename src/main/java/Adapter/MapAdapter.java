package Adapter;

import Models.Maps;

/**
 * The MapAdapter class acts as an adapter allowing the game to use the
 * DominationMapIO interface's load and save methods through the ConquestMapIO interface.
 * This class extends ConquestMapIO and internally uses an instance of DominationMapIO
 * to perform its operations, enabling interoperability between Conquest and Domination
 * map formats.
 */
public class MapAdapter extends ConquestMapIO {
    /**
     * An instance of DominationMapIO used to delegate the load and save operations.
     */
    private DominationMapIO d_dominationMapIO;

    /**
     * Constructs a MapAdapter instance with a specified DominationMapIO.
     *
     * @param p_dominationMapIO The DominationMapIO instance to use for loading and saving maps.
     */
    public MapAdapter(DominationMapIO p_dominationMapIO) {
        this.d_dominationMapIO = p_dominationMapIO;
    }


    /**
     * Loads a map from a file into the provided Maps object using the DominationMapIO's load method.
     * This method overrides the ConquestMapIO's loadMap method to adapt between the Domination and Conquest formats.
     *
     * @param p_gameMap The Maps object to populate with the loaded map data.
     * @param p_fileName The name of the file from which to load the map.
     */
    @Override
    public void loadMap(Maps p_gameMap, String p_fileName) {
            d_dominationMapIO.loadMap(p_gameMap, p_fileName);
    }


    /**
     * Saves the current state of the game map, represented by the provided Maps object, to a file using the
     * DominationMapIO's save method. This method overrides the ConquestMapIO's saveMap method to adapt between the
     * Domination and Conquest formats.
     *
     * @param p_gameMap The Maps object containing the map data to be saved.
     * @param p_fileName The name of the file to which the map data will be saved.
     */
    @Override
    public void saveMap(Maps p_gameMap, String p_fileName){
            d_dominationMapIO.saveMap(p_gameMap, p_fileName);
    }
}
