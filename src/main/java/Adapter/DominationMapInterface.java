package Adapter;

import Models.Maps;
/**
 * The DominationMapInterface defines the operations for loading and saving game maps specific to the Domination game mode.
 * Implementations of this interface should encapsulate the logic necessary for interpreting and persisting game maps in
 * a format that is compatible with the Domination mode. This interface serves as an adapter, enabling the game's core
 * mechanisms to work with Domination-specific map formats.
 */
public interface DominationMapInterface {
    /**
     * Loads the map data from a specified file into the provided Maps object. Implementations are expected to parse the
     * file contents, interpreting them according to the Domination map format, and populate the given Maps object with
     * the resulting map data.
     *
     * @param p_gameMap The Maps object to be filled with the map data. This object acts as the in-memory representation
     *                  of the game map.
     * @param p_fileName The name (and potentially the path) of the file from which to load the map. The file's format
     *                   should adhere to the specifications set for Domination maps.
     */
    public void loadMap(Maps p_gameMap, String p_fileName);

    /**
     * Saves the state of the game map, as represented by the provided Maps object, to a file. Implementations should
     * convert the map data within the Maps object into a Domination-compatible map format and write this to the
     * specified file.
     *
     * @param p_gameMap The Maps object containing the data of the game map to be saved. This object encapsulates all
     *                  necessary details for reconstructing the map.
     * @param p_fileName The name (and potentially the path) of the file where the map is to be saved. The saved map
     *                   should be in a format that complies with the Domination map specifications.
     */
    public void saveMap(Maps p_gameMap, String p_fileName);
}
