package Adapter;

import Models.Maps;

/**
 * The ConquestMapInterface defines the contract for loading and saving game maps specific to the Conquest game mode.
 * Implementations of this interface should provide the necessary logic to interpret and persist game maps in a format
 * compatible with the Conquest mode, thereby acting as an adapter that allows the game's core logic to interact with
 * maps designed for this mode.
 */
public interface ConquestMapInterface {

    /**
     * Loads a map from a specified file into the provided Maps object. Implementations should read the file, interpret
     * its contents according to the Conquest map format, and populate the provided Maps object with the map data.
     *
     * @param p_gameMap The Maps object to be populated with map data. This object represents the game map in memory.
     * @param p_fileName The name (and potentially the path) of the file from which the map is to be loaded. The format
     *                   of the file is expected to be compatible with Conquest map specifications.
     */
    public void loadMap(Maps p_gameMap, String p_fileName);

    /**
     * Saves the current state of the game map to a file. Implementations should serialize the map data contained in
     * the provided Maps object into a format compatible with Conquest map specifications and write it to the specified
     * file.
     *
     * @param p_gameMap The Maps object containing the game map data to be saved. This object represents the game map
     *                  in memory and should contain all the necessary information to recreate the map.
     * @param p_fileName The name (and potentially the path) of the file to which the map is to be saved. The map should
     *                   be saved in a format that is compatible with Conquest map specifications.
     */
    public void saveMap(Maps p_gameMap, String p_fileName);
}
