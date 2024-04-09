/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */

package Services;

import Constants.AppConstants;
import Models.GameModel;

import java.io.*;

/**
 * The GameLoader class provides static methods for saving and loading game data to and from files.
 * <p>
 * It utilizes Java's serialization mechanism to save and load instances of GameModel to and from binary files.
 * </p>
 */
public class GameLoader {

    /**
     * Saves the specified game model to a binary file.
     *
     * @param p_gameModel The game model to be saved.
     * @param p_fileName  The name of the file to which the game model will be saved.
     * @throws IOException If an I/O error occurs while saving the game model.
     */
    public static void SaveGame(Serializable p_gameModel, String p_fileName) throws IOException {
        FileOutputStream l_fos = new FileOutputStream(AppConstants.savedFilesPath + p_fileName + ".bin");
        ObjectOutputStream l_oos = new ObjectOutputStream(l_fos);
        l_oos.writeObject(p_gameModel);
        l_fos.close();
    }

    /**
     * Loads a game model from a binary file.
     *
     * @param p_fileName The name of the file from which the game model will be loaded.
     * @return The loaded GameModel object.
     * @throws IOException            If an I/O error occurs while loading the game model.
     * @throws ClassNotFoundException If the class of the serialized object cannot be found.
     */
    public static GameModel LoadGame(String p_fileName) throws IOException, ClassNotFoundException {
        FileInputStream l_fis = new FileInputStream(AppConstants.savedFilesPath + p_fileName + ".bin");
        ObjectInputStream l_ois = new ObjectInputStream(l_fis);
        return (GameModel) l_ois.readObject();
    }
}
