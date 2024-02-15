import Controller.GameEngineController;
import Models.Map;
import Views.MapView;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameEngineController engine = new GameEngineController();
            Map map = new Map();
            map.loadMap("src/main/resources/Maps/canada.map");
            MapView mapView=new MapView();
            mapView.showMap(map.getContinents(),map.getCountries());
        } catch (IOException e) {
            System.out.println(e);

        }
    }
}