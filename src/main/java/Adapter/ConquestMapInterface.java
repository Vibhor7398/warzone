package Adapter;

import Models.Maps;

public interface ConquestMapInterface {
    public void loadMap(Maps p_gameMap, String p_fileName);
    public void saveMap(Maps p_gameMap, String p_fileName);
}
