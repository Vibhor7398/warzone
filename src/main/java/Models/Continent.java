package Models;
import java.util.LinkedHashMap;

public class Continent {
    private final LinkedHashMap<String, Country> d_countries;
    private int d_id;
    private String d_name;
    private final int d_continentValue;
    private String d_color;

    public Continent(String p_name,int p_controlValue) {
        this.d_name = p_name;
        this.d_continentValue = p_controlValue;
        d_countries = new LinkedHashMap<>();
    }

    public Continent(int p_id, String p_name, int p_controlValue, String p_color) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_color = p_color;
        this.d_continentValue = p_controlValue;
        d_countries = new LinkedHashMap<>();
    }
    public int getId() {
        return d_id;
    }

    public String getName() {
        return d_name;
    }

    public int getContinentValue() {
        return this.d_continentValue;
    }

    public void setName(String p_name) {
        this.d_name = p_name;
    }

    public LinkedHashMap<String, Country> getCountries() {
        return d_countries;
    }
    public void addCountry(Country p_country) {
        d_countries.put(p_country.getName(), p_country);
    }

    public String getColor() {
        return d_color;
    }
}
