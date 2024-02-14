package Models;

import java.util.ArrayList;

public class Continent {
    private String d_name;
    private ArrayList<Country> d_countries;

    public Continent(String p_name, int p_controlValue) {
        this.d_name = p_name;
        d_countries = new ArrayList<Country>();
    }

    public Continent() {
        d_countries = new ArrayList<Country>();
    }

    public String getName() {
        return d_name;
    }

    public void setName(String p_name) {
        this.d_name = p_name;
    }

    public ArrayList<Country> getCountries(){
        return d_countries;
    }

    public void addCountry(Country p_country){
        d_countries.add(p_country);
    }

}
