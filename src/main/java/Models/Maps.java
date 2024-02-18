package Models;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class Maps {
    private static LinkedHashMap<String, Continent> d_continents;
    private static LinkedHashMap<String, Country> d_countries;
    private boolean d_isMapValid;
    private boolean d_mapFileLoaded;

    public Maps() {
        this.d_continents = new LinkedHashMap<>();
        this.d_countries = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Continent> getContinents() {
        return d_continents;
    }

    public LinkedHashMap<String, Country> getCountries() {
        return d_countries;
    }

    private Country findCountryById(String id) {
        for (Country country : d_countries.values()) {
            if (String.valueOf(country.getId()).equals(id)) {
                return country;
            }
        }
        return null;
    }

    private Country findCountryByName(String name) {
        for (Country country : d_countries.values()) {
            if (String.valueOf(country.getName()).equals(name)) {
                return country;
            }
        }
        return null;
    }

    public boolean getMapValid() {
        return this.d_isMapValid;
    }

    public void setMapValid(boolean p_isMapValid) {
        this.d_isMapValid = p_isMapValid;
    }

}






