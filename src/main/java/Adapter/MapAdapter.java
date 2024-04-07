package Adapter;

import Models.Maps;

public class MapAdapter extends ConquestMapIO {
    private DominationMapIO d_dominationMapIO;

    public MapAdapter(DominationMapIO p_dominationMapIO) {
        this.d_dominationMapIO = p_dominationMapIO;
    }

    @Override
    public void loadMap(Maps p_gameMap, String p_fileName) {
            d_dominationMapIO.loadMap(p_gameMap, p_fileName);
    }

    @Override
    public void saveMap(Maps p_gameMap, String p_fileName){
            d_dominationMapIO.saveMap(p_gameMap, p_fileName);
    }
}
