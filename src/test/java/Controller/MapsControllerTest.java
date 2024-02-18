package Controller;

import Services.CommandValidationService;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
public class MapsControllerTest {
    private MapsController d_mapsController;
    @Before
    public void setUp()  {
        d_mapsController = new MapsController();
    }
    @Test
    public void validateMap_ValidMap() {
        try {
            d_mapsController.editMap(new File("test.map"));
            d_mapsController.addContinent("Asia", 5);
            d_mapsController.addCountry("India", "Asia");
            d_mapsController.addCountry("China", "Asia");
            d_mapsController.editNeighbors("add", "India", "China");
            d_mapsController.editNeighbors("add", "China", "India");
            d_mapsController.validateMap();
            boolean l_isValid=d_mapsController.isMapValid();
            assertTrue("Map is valid",l_isValid);
        }catch (IOException e){
            fail("No map found" );

        }
    }

    @Test
    public void validateMap_ContinentValidation() {
        try {
            d_mapsController.editMap(new File("test.map"));
            d_mapsController.addContinent("Asia", 5);
            d_mapsController.addCountry("India", "Asia");
            d_mapsController.addCountry("China", "Asia");
            d_mapsController.validateMap();
            boolean l_isValid=d_mapsController.isMapValid();
            assertFalse("Map has a disconnected subgraph",l_isValid);
        }catch (IOException e){
            fail("No map found" );

        }
    }

    @Test
    public void addNeighbor_Success() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "India", "China");
        assertTrue("China should be a neighbor of India",
                d_mapsController.getD_countries().get("India").getNeighbors().containsKey("China"));
    }

    @Test
    public void removeNeighbor_Success() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "India", "China");
        d_mapsController.editNeighbors("remove", "India", "China");
        assertFalse("China should no longer be a neighbor of India",
                d_mapsController.getD_countries().get("India").getNeighbors().containsKey("China"));
    }

    @Test
    public void addNeighbor_InvalidCountry() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "NonExistentCountry", "China");
        assertTrue("NonExistentCountry does not exist, so no operation should have occurred",
                d_mapsController.getD_countries().get("China").getNeighbors().isEmpty());
    }

    @Test
    public void addNeighbor_InvalidNeighbor() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "India", "NonExistentNeighbor");
        assertTrue("No valid neighbors should be added to India",
                d_mapsController.getD_countries().get("India").getNeighbors().isEmpty());
    }

    @Test
    public void removeNeighbor_Nonexistent() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("remove", "India", "China");
        assertTrue("Operation should not affect the map as the neighbor does not exist",
                d_mapsController.getD_countries().get("India").getNeighbors().isEmpty());
    }
}