package Adapter;

import Constants.AppConstants;
import Models.Maps;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DominationMapIOTest {

    private DominationMapIO dominationMapIO;
    private Maps gameMap;

    @Before
    public void setUp() {
        dominationMapIO = new DominationMapIO();
        gameMap = new Maps();
    }

    @Test
    public void testLoadMap_ValidMap() {
        boolean mapLoaded = dominationMapIO.loadMap(gameMap, AppConstants.MapsPath + "world.map");
        assertTrue(mapLoaded);
    }

    @Test
    public void testLoadMap_InvalidMap() {
        boolean mapLoaded = dominationMapIO.loadMap(gameMap, "broo.map");
        assertFalse(mapLoaded);
    }

    @Test
    public void testSaveMap_ValidMap() {
        dominationMapIO.loadMap(gameMap, "world.map");
        boolean mapSaved = dominationMapIO.saveMap(gameMap, "world-clone.map");
        assertTrue(mapSaved);
    }
}
