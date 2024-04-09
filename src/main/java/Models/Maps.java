/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */

package Models;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Represents the collection of continents and countries in the game map.
 */
public class Maps implements Serializable {
    private LinkedHashMap<String, Continent> d_Continents;
    private LinkedHashMap<String, Country> d_Countries;
    private boolean d_isMapValid;

    /**
     * Constructs a new Maps object.
     */
    public Maps() {
        d_Continents = new LinkedHashMap<>();
        d_Countries = new LinkedHashMap<>();
    }

    /**
     * Returns the map of continents.
     *
     * @return The map of continents.
     */
    public LinkedHashMap<String, Continent> getContinents() {
        return d_Continents;
    }

    /**
     * Returns the map of countries.
     *
     * @return The map of countries.
     */
    public LinkedHashMap<String, Country> getCountries() {
        return d_Countries;
    }

    /**
     * Returns whether the map is valid.
     *
     * @return true if the map is valid, false otherwise.
     */
    public boolean getMapValid() {
        return this.d_isMapValid;
    }

    /**
     * Sets whether the map is valid.
     *
     * @param p_isMapValid true if the map is valid, false otherwise.
     */
    public void setMapValid(boolean p_isMapValid) {
        this.d_isMapValid = p_isMapValid;
    }

    /**
     * Resets the given instance of the map
     *
     */
    public void resetMap(){
        d_Continents.clear();
        d_Countries.clear();
    }
}

