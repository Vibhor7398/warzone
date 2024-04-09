package Adapter;

import Constants.AppConstants;
import Models.Maps;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class ConquestMapIOTest {

    private ConquestMapIO d_conquestMapIO;
    private Maps d_gameMap;

    @Before
    public void setUp() {
        d_conquestMapIO = new ConquestMapIO();
        d_gameMap = new Maps();
    }

    @Test
    public void testLoadMap_ValidMap() {
        boolean mapLoaded = d_conquestMapIO.loadMap(d_gameMap, AppConstants.MapsPath + "brasil.map");
        assertTrue(mapLoaded);
    }
    @Test
    public void testLoadMap_InvalidMap() {
        boolean mapLoaded = d_conquestMapIO.loadMap(d_gameMap, "broo.map");
        assertFalse(mapLoaded);
    }

    @Test
    public void testSaveMap_ValidMap() {
        d_conquestMapIO.loadMap(d_gameMap, "brasil.map");
        boolean mapSaved=d_conquestMapIO.saveMap(d_gameMap,"brasil-clone.map");
        assertTrue(mapSaved);
    }

}
