package Services;

import Constants.AppConstants;
import Models.GameModel;

import java.io.*;

public class GameLoader {
    public static void SaveGame(Serializable p_gameModel, String p_fileName) throws IOException {
        FileOutputStream l_fos = new FileOutputStream(AppConstants.savedFilesPath+p_fileName+".bin");
        ObjectOutputStream l_oos = new ObjectOutputStream(l_fos);
        l_oos.writeObject(p_gameModel);
        l_fos.close();
    }

    public static GameModel LoadGame(String p_fileName) throws IOException, ClassNotFoundException {
        FileInputStream l_fis = new FileInputStream(AppConstants.savedFilesPath+p_fileName+".bin");
        ObjectInputStream l_ois = new ObjectInputStream(l_fis);
        return (GameModel) l_ois.readObject();
    }
}