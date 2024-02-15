package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private String d_name;
    private HashMap<Integer, Country> d_countryList;

    private int d_armies;

    public Player(String p_name) {
        this.setName(p_name);
        d_countryList = new HashMap<Integer, Country>();
        d_armies=0;
    }
    public void setName(String p_name) {
        this.d_name = p_name;
    }
    public void addCountry(Country p_country) {
        this.d_countryList.put(p_country.getId(), p_country);
    }
    public Country getCountry(int p_countryId) {
        return d_countryList.get(p_countryId);
    }
    
    public ArrayList<Country> getCountries(){
        return new ArrayList<Country>(this.d_countryList.values());
    }

    public boolean containsCountry(int p_countryId){
        return d_countryList.containsKey(p_countryId);
    }





}
