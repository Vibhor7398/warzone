package Adapter;

import Models.Maps;
import java.io.IOException;

public class MapAdapter implements ConquestMapInterface {
    private DominationMapIO d_dominationMapIO;

    public MapAdapter(DominationMapIO p_dominationMapIO) {
        this.d_dominationMapIO = p_dominationMapIO;
    }

    @Override
    public void loadMap(Maps p_gameMap, String p_fileName) {
        try {
            d_dominationMapIO.loadMap(p_gameMap, p_fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveMap(Maps p_gameMap, String p_fileName){
        try {
            d_dominationMapIO.saveMap(p_gameMap, p_fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
