/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */
package Controller;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * This class provides unit tests for the MapsController class.
 * It covers various functionalities such as validating maps, adding and removing continents, adding and removing neighbors, etc.
 */
public class TestMapsController {
    private MapsController d_mapsController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    /**
     * Sets up the test environment by creating a new MapsController instance and redirecting System.out to outContent.
     */
    @Before
    public void setUp()  {
        d_mapsController = new MapsController();
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tests the validation of a valid map.
     * It creates a map with continents and countries, adds neighbors, and validates the map.
     * The test checks if the map is considered valid.
     */
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
    /**
     * Tests the validation of a map with a disconnected subgraph.
     * It creates a map with a continent but no connections between countries.
     * The test checks if the map is considered invalid due to disconnected subgraphs.
     */
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
    /**
     * Tests the addition of a continent.
     * It adds a continent and checks if it exists in the map.
     */
    @Test
    public void addContinent_Success() {
        d_mapsController.addContinent("Asia", 5);
        assertTrue("Continent should be added",d_mapsController.continentAlreadyExists("Asia"));
    }

    /**
     * Tests the addition of a continent that already exists.
     * It adds a continent and then tries to add it again.
     * The test checks if the continent is not added again and a message is displayed.
     */
    @Test
    public void addContinent_AlreadyExists() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addContinent("Asia", 5);
        assertTrue("Continent should be added", d_mapsController.continentAlreadyExists("Asia"));
        String expectedOutput = "The continent(Asia)you are trying to add already exist!";
        assertTrue("Expected duplicate addition message", outContent.toString().trim().contains(expectedOutput));
    }

    /**
     * Tests the removal of a continent.
     * It adds a continent and then removes it.
     * The test checks if the continent is removed from the map.
     */
    @Test
    public void removeContinent_Success() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.removeContinent("Asia");
        assertFalse("Continent should be removed", d_mapsController.continentAlreadyExists("Asia"));
    }

    /**
     * Tests the removal of a continent that does not exist.
     * It tries to remove a continent that does not exist.
     * The test checks if a message is displayed indicating that the continent does not exist.
     */
    @Test
    public void removeContinent_Failed() {
        d_mapsController.removeContinent("Asia");
        String expectedOutput = "Continent that you are trying to remove does not exist!";
        assertTrue("Continent exists!", outContent.toString().trim().contains(expectedOutput));
    }

    /**
     * Tests the addition of a country.
     * It adds a continent and then adds a country to it.
     * The test checks if the country is added to the map.
     */
    @Test
    public void addNeighbor_Success() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "India", "China");
        assertTrue("China should be a neighbor of India",
                d_mapsController.getD_countries().get("India").getNeighbors().containsKey("China"));
    }

    /**
     * Tests the removal of a neighbor.
     * It adds a continent and then adds neighbors to it.
     * The test checks if the neighbors are removed from the map.
     */
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
    /**
     * Tests the addition of a neighbor that does not exist.
     * It adds a continent and then tries to add a neighbor that does not exist.
     * The test checks if the neighbor is not added and a message is displayed.
     */

    @Test
    public void addNeighbor_InvalidCountry() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "NonExistentCountry", "China");
        assertTrue("NonExistentCountry does not exist, so no operation should have occurred",
                d_mapsController.getD_countries().get("China").getNeighbors().isEmpty());
    }

    /**
     * Tests the addition of a neighbor that does not exist.
     * It adds a continent and then tries to add a neighbor that does not exist.
     * The test checks if the neighbor is not added and a message is displayed.
     */
    @Test
    public void addNeighbor_InvalidNeighbor() {
        d_mapsController.addContinent("Asia", 5);
        d_mapsController.addCountry("India", "Asia");
        d_mapsController.addCountry("China", "Asia");
        d_mapsController.editNeighbors("add", "India", "NonExistentNeighbor");
        assertTrue("No valid neighbors should be added to India",
                d_mapsController.getD_countries().get("India").getNeighbors().isEmpty());
    }

    /**
     * Tests the removal of a neighbor that does not exist.
     * It adds a continent and then tries to remove a neighbor that does not exist.
     * The test checks if the neighbor is not removed and a message is displayed.
     */
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