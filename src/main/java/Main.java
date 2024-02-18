import Controller.MapsController;
import Models.Maps;

import java.io.File;
import java.io.IOException;
import java.util.OptionalInt;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try {

            MapsController mapsController=new MapsController();
            mapsController.loadMap("src/main/resources/Maps/canada.map");
            mapsController.validateMap();
            System.out.println(mapsController.isMapValid());
            mapsController.editNeighbors("add","New_Brunswick","N&L-Newfoundland");
            mapsController.addContinent("Africa", 3);
           mapsController.addCountry("India", "Africa");
//            mapsController.showMap();
            mapsController.showMap();
            mapsController.validateMap();
            mapsController.saveMap(new File("src/main/resources/Maps/canananan.map"));
            System.out.println(mapsController.isMapValid());
//            System.out.println(mapsController.isMapValid());

//            GameEngineController engine = new GameEngineController();
//            MapController mapController=new MapController();
//            Maps map = new Maps();
//            map.loadMap("src/main/resources/Maps/canada.map");
//            mapController.showMap(map.getContinents());
//            map.validateMap();
//            map.saveMap(new File("src/main/resources/Maps/canada-copy.map"));
//            System.out.println(map.isMapValid());
        } catch (IOException e) {
            System.out.println(e);

        }
    }
}