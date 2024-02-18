import Controller.GameEngineController;
import Models.Maps;
import Views.MapView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.OptionalInt;

public class Main {
    public static void main(String[] args) {
        try {
            GameEngineController engine = new GameEngineController();
            Maps map = new Maps();
            map.loadMap("src/main/resources/Maps/canada.map");
            MapView mapView=new MapView();
            mapView.showMap(map.getContinents(),map.getCountries());
            map.editContinent("add", "Africa", 4);

            mapView.showMap(map.getContinents(),map.getCountries());

            map.editContinent("remove", "Atlantic_Provinces");

            mapView.showMap(map.getContinents(),map.getCountries());

            map.editCountry("add", "Paris", OptionalInt.of(3));
            
            mapView.showMap(map.getContinents(),map.getCountries());

            map.editCountry("remove", "BC-Vancouver_Island", OptionalInt.empty());
            
            mapView.showMap(map.getContinents(),map.getCountries());

            map.editNeighbors("add", "New_Brunswick", "N&L-Newfoundland");
            
        } catch (IOException e) {
            System.out.println(e);

        }
    }
}