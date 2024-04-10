/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */

package Services;

import Models.GameModel;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * JUnit test class for the GameLoader class.
 * This class provides test cases to verify the functionality of the GameLoader class.
 */
public class GameLoaderTest {
    private GameModel gameModel;
    private String fileName;

    /**
     * Sets up the test environment by initializing necessary objects.
     */
    @Before
    public void setUp() {
        gameModel = new GameModel();
        fileName = "testFile";
    }

    /**
     * Tests the successful saving of a game.
     * Verifies that the SaveGame method does not throw an IOException.
     */
    @Test
    public void saveGameSuccessfully() {
        try {
            GameLoader.SaveGame(gameModel, fileName);
        } catch (IOException e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Tests the successful loading of a game.
     * Verifies that the LoadGame method does not throw IOException or ClassNotFoundException.
     */
    @Test
    public void loadGameSuccessfully() {
        try {
            saveGameSuccessfully();
            GameLoader.LoadGame(fileName);
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception should not be thrown");
        }
    }

    /**
     * Tests loading a game with a non-existent file.
     * Verifies that IOException is thrown when attempting to load a non-existent file.
     * @throws IOException if the file does not exist or cannot be read
     * @throws ClassNotFoundException if the class of the serialized object cannot be found
     */
    @Test(expected = IOException.class)
    public void loadGameWithNonExistentFile() throws IOException, ClassNotFoundException {
        GameLoader.LoadGame("nonExistentFile");
    }
}
