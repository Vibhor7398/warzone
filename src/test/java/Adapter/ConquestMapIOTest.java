package Adapter;

import Constants.AppConstants;
import Models.Maps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains unit tests for the ConquestMapIO class.
 * It tests the loading and saving functionalities of the ConquestMapIO class.
 */
public class ConquestMapIOTest {

    private ConquestMapIO d_conquestMapIO;
    private Maps d_gameMap;

    /**
     * Set up the necessary objects for testing.
     */
    @Before
    public void setUp() {
        d_conquestMapIO = new ConquestMapIO();
        d_gameMap = new Maps();
    }

    /**
     * Test loading a valid map file.
     * Expected: The map should be successfully loaded.
     */
    @Test
    public void testLoadMap_ValidMap() {
        boolean mapLoaded = d_conquestMapIO.loadMap(d_gameMap, AppConstants.MapsPath + "brasil.map");
        assertTrue(mapLoaded);
    }

    /**
     * Test loading an invalid map file.
     * Expected: The map should not be loaded.
     */
    @Test
    public void testLoadMap_InvalidMap() {
        boolean mapLoaded = d_conquestMapIO.loadMap(d_gameMap, "broo.map");
        assertFalse(mapLoaded);
    }

    /**
     * Test saving a valid map file.
     * Expected: The map should be successfully saved.
     */
    @Test
    public void testSaveMap_ValidMap() {
        d_conquestMapIO.loadMap(d_gameMap, "brasil.map");
        boolean mapSaved = d_conquestMapIO.saveMap(d_gameMap, "brasil-clone.map");
        assertTrue(mapSaved);
    }
}
