package Controller;
import Models.Maps;
import Models.Continent;
import Models.Country;
import java.util.LinkedHashMap;

public class MapsController {
    private Maps maps;

    public MapsController() {
        this.maps=new Maps();
    }





    public void showMap() {
        LinkedHashMap<String, Continent> continent=maps.getContinents();
        continent.values().forEach(p_continent -> {
            System.out.println("ID: " + p_continent.getId() + " | Name: " + p_continent.getName() +
                    " | Control Value: " + p_continent.getContinentValue() +
                    " | Number of Countries: " + p_continent.getCountries().size());
            p_continent.getCountries().forEach((countryId, country) -> {
                System.out.println("\tCountry ID: " + country.getId() + " | Name: " + country.getName());
                LinkedHashMap<String, Country> neighborsMap = country.getNeighbors();
                if (!neighborsMap.isEmpty()) {
                    String neighbors = String.join(", ", neighborsMap.keySet());
                    System.out.println("\t\tNeighbors: " + neighbors);
                } else {
                    System.out.println("\t\tNeighbors: None");
                }
            });
            System.out.println();
        });
    }
}
