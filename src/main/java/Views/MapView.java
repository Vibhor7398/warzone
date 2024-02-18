package Views;


import Models.Continent;
import Models.Country;


import java.util.HashMap;


public class MapView {
    public void showContinents(HashMap<String, Continent> p_continents){
        System.out.println("Displaying Continents");
        System.out.println("ID|Name| Control Value| Number of Countries");
        p_continents.values().forEach(p_continent->{
            System.out.print(String.valueOf(p_continent.getId())+"|");
            System.out.print(p_continent.getName()+"|");
            System.out.print(p_continent.getContinentValue()+"|");
            System.out.print(p_continent.getCountries().size()+"|\n");
        });
        System.out.println();
    }
    public void showCountries(HashMap<String, Country> p_countries, HashMap<String,Continent> p_continents){
        System.out.println("Displaying Countries");
        System.out.println("ID|Name|Control Value|Countries");
        p_countries.values().forEach(p_country->{
            System.out.print(String.valueOf(p_country.getId())+"|");
            System.out.print(p_country.getName()+"| \n");
        });
        System.out.println();
    }


    public void showMap(HashMap<String,Continent> p_continents,HashMap<String,Country> p_countries){
        showContinents(p_continents);
        showCountries(p_countries,p_continents);
    }
}

