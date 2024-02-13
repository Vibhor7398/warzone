package Models;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerModel {
    private String d_name;
    private HashMap<Integer, CountryModel> d_countryList;

    public PlayerModel(String p_name) {
        this.setName(p_name);
        d_countryList = new HashMap<Integer, CountryModel>();
    }
    public void setName(String p_name) {
        this.d_name = p_name;
    }
    public void addCountry(CountryModel p_countryModel) {
        this.d_countryList.put(p_countryModel.getId(),p_countryModel);
    }
    public CountryModel getCountry(int p_countryId) {
        return d_countryList.get(p_countryId);
    }
    
    public ArrayList<CountryModel> getCountries(){
        return new ArrayList<CountryModel>(this.d_countryList.values());
    }

    public boolean containsCountry(int p_countryId){
        return d_countryList.containsKey(p_countryId);
    }





}
