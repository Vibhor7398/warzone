package Models;

import java.util.*;
public class Maps {
    private static LinkedHashMap<String, Continent> d_Continents;
    private static LinkedHashMap<String, Country> d_Countries;
    private boolean d_isMapValid;

    public Maps() {
        d_Continents = new LinkedHashMap<>();
        d_Countries = new LinkedHashMap<>();
    }
    public LinkedHashMap<String, Continent> getContinents() {
        return d_Continents;
    }

    public LinkedHashMap<String, Country> getCountries() {
        return d_Countries;
    }

    public boolean getMapValid() {
        return this.d_isMapValid;
    }
    public void setMapValid(boolean p_isMapValid) {
        this.d_isMapValid = p_isMapValid;
    }
}






