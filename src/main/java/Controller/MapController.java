package Controller;

import Models.Continent;
import Models.Country;
import java.util.HashMap;
import java.util.Scanner;

public class MapController {
    private HashMap<String, Continent> d_continents;
    private HashMap<String, Country> d_countries;

    public MapController() {
        this.d_continents = new HashMap<String, Continent>();
        this.d_countries = new HashMap<String, Country>();
    }

    public HashMap<String, Continent> getContinents() {
        return d_continents;
    }

    public HashMap<String, Country> getCountries() {
        return d_countries;
    }

 }
