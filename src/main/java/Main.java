import Controller.GameEngineController;
import Models.Maps;
import Views.MapView;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameEngineController engine = new GameEngineController();
            Maps map = new Maps();
            map.loadMap("src/main/resources/Maps/canada.map");
            MapView mapView=new MapView();
            mapView.showMap(map.getContinents(),map.getCountries());
        } catch (IOException e) {
            System.out.println(e);

        }
    }
}