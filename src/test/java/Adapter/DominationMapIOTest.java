package Adapter;

import Constants.AppConstants;
import Models.Maps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
 * This class contains unit tests for the DominationMapIO class.
 * It tests the loading and saving functionalities of the DominationMapIO class.
 */
public class DominationMapIOTest {

    private DominationMapIO dominationMapIO;
    private Maps gameMap;

    /**
     * Set up the necessary objects for testing.
     */
    @Before
    public void setUp() {
        dominationMapIO = new DominationMapIO();
        gameMap = new Maps();
    }

    /**
     * Test loading a valid map file.
     * Expected: The map should be successfully loaded.
     */
    @Test
    public void testLoadMap_ValidMap() {
        boolean mapLoaded = dominationMapIO.loadMap(gameMap, AppConstants.MapsPath + "world.map");
        assertTrue(mapLoaded);
    }

    /**
     * Test loading an invalid map file.
     * Expected: The map should not be loaded.
     */
    @Test
    public void testLoadMap_InvalidMap() {
        boolean mapLoaded = dominationMapIO.loadMap(gameMap, "broo.map");
        assertFalse(mapLoaded);
    }

    /**
     * Test saving a valid map file.
     * Expected: The map should be successfully saved.
     */
    @Test
    public void testSaveMap_ValidMap() {
        dominationMapIO.loadMap(gameMap, "world.map");
        boolean mapSaved = dominationMapIO.saveMap(gameMap, "world-clone.map");
        assertTrue(mapSaved);
    }
}
