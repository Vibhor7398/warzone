/**
 *
 * 
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */

package Models;
import java.util.LinkedHashMap;

/**
 * This class represents a continent in the game map.
 * It contains information about the countries it contains, its ID, name, control value, and color.
 */
public class Continent {
    private final LinkedHashMap<String, Country> d_countries;
    private int d_id;
    private String d_name;
    private final int d_continentValue;
    private final String d_color;

    /**
     * Constructs a Continent object with the specified ID, name, control value, and color.
     * @param p_id The ID of the continent.
     * @param p_name The name of the continent.
     * @param p_controlValue The control value of the continent.
     * @param p_color The color associated with the continent.
     */
    public Continent(int p_id, String p_name, int p_controlValue, String p_color) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_color = p_color;
        this.d_continentValue = p_controlValue;
        d_countries = new LinkedHashMap<>();
    }

    /**
     * Gets the ID of the continent.
     * @return The ID of the continent.
     */
    public int getId() {
        return d_id;
    }

    /**
     * Gets the name of the continent.
     * @return The name of the continent.
     */
    public String getName() {
        return d_name;
    }

    /**
     * Gets the control value of the continent.
     * @return The control value of the continent.
     */
    public int getContinentValue() {
        return this.d_continentValue;
    }

    /**
     * Gets the countries within the continent.
     * @return A LinkedHashMap containing the countries within the continent.
     */
    public LinkedHashMap<String, Country> getCountries() {
        return d_countries;
    }

    /**
     * Adds a country to the continent.
     * @param p_country The country to add.
     */
    public void addCountry(Country p_country) {
        d_countries.put(p_country.getName(), p_country);
    }

    /**
     * Gets the color associated with the continent.
     * @return The color associated with the continent.
     */
    public String getColor() {
        return d_color;
    }

    /**
     * Removes a country from the continent.
     * @param p_country The name of the country to remove.
     */
    public void removeCountry(String p_country) {
        d_countries.remove(p_country);
    }
}
