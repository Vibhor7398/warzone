import Controller.GameEngineController;
import Models.Map;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameEngineController engine = new GameEngineController();
            Map map = new Map();
            map.loadMap(new File("src/main/resources/Maps/canada.map"));
        } catch (IOException e) {
            System.out.println(e);

        }
    }
}