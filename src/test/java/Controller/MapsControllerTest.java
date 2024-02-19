package Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;
public class MapsControllerTest {
    private MapsController d_mapsController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @Before
    public void setUp()  {
        d_mapsController = new MapsController();
        System.setOut(new PrintStream(outContent));

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
    public void addContinent_Success() {
        d_mapsController.addContinent("Asia", 5);
        assertTrue("Continent should be added",d_mapsController.continentAlreadyExists("Asia"));
    }

    @Test
    public void addContinent_AlreadyExists() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addContinent("Asia", 5);
        assertTrue("Continent should be added", d_mapsController.continentAlreadyExists("Asia"));
        String expectedOutput = "The continent(Asia)you are trying to add already exist!";
        assertTrue("Expected duplicate addition message", outContent.toString().trim().contains(expectedOutput));
    }

    @Test
    public void removeContinent_Success() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.removeContinent("Asia");
        assertFalse("Continent should be removed", d_mapsController.continentAlreadyExists("Asia"));
    }

    @Test
    public void removeContinent_Failed() {
        d_mapsController.removeContinent("Asia");
        String expectedOutput = "Continent that you are trying to remove does not exist!";
        assertTrue("Continent exists!", outContent.toString().trim().contains(expectedOutput));
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