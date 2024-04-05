package Adapter;

import Models.Maps;
import java.io.IOException;

public class MapAdapter extends ConquestMapIO {
    private DominationMapIO d_dominationMapIO;

    public MapAdapter(DominationMapIO p_dominationMapIO) {
        this.d_dominationMapIO = p_dominationMapIO;
    }

    @Override
    public void loadMap(Maps p_gameMap, String p_fileName) throws IOException {
        d_dominationMapIO.loadMap(p_gameMap, p_fileName);
    }

    @Override
    public void saveMap(Maps p_gameMap, String p_fileName) throws IOException {
        d_dominationMapIO.saveMap(p_gameMap, p_fileName); // Corrected method call with the correct parameters.
    }
}
