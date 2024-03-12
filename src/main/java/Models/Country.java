/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 1.0
 */

package Models;

import java.util.LinkedHashMap;

/**
 * This class represents a country in the game map.
 * It contains information about the country's ID, name, continent ID, coordinates, armies, and neighboring countries.
 */
public class Country {
    private int d_id;
    private String d_name;
    private String d_continentId;
    private int d_armies;
    private String d_xCoordinate;
    private String d_yCoordinate;
    private LinkedHashMap<Country, LinkedHashMap<String, Country>> d_neighbors;

    /**
     * Constructs a Country object with the specified ID, name, continent ID, x-coordinate, and y-coordinate.
     * @param p_id The ID of the country.
     * @param p_name The name of the country.
     * @param p_continentId The ID of the continent to which the country belongs.
     * @param p_xCoordinate The x-coordinate of the country.
     * @param p_yCoordinate The y-coordinate of the country.
     */
    public Country(int p_id, String p_name, String p_continentId, String p_xCoordinate, String p_yCoordinate) {
        this.d_id = p_id;
        this.d_name = p_name;
        this.d_continentId = p_continentId;
        this.d_xCoordinate = p_xCoordinate;
        this.d_yCoordinate = p_yCoordinate;
        this.d_armies = 0;
        this.d_neighbors = new LinkedHashMap<>();
        this.d_neighbors.put(this, new LinkedHashMap<>());
    }

    public Country(String p_name) {
        d_name = p_name;
    }

    /**
     * Gets the ID of the country.
     * @return The ID of the country.
     */
    public int getId() {
        return d_id;
    }

    /**
     * Gets the name of the country.
     * @return The name of the country.
     */
    public String getName() {
        return d_name;
    }

    /**
     * Gets the number of armies in the country.
     * @return The number of armies in the country.
     */
    public int getArmies(){
        return this.d_armies;
    }

    /**
     * Sets the number of armies in the country.
     * @param p_armies The number of armies to set.
     */
    public void setArmies(int p_armies){
         this.d_armies=p_armies;
    }

    /**
     * Gets the continent ID of the country.
     * @return The continent ID of the country.
     */
    public String getContinentId() {
        return d_continentId;
    }

    /**
     * Gets the x-coordinate of the country.
     * @return The x-coordinate of the country.
     */
    public String getXCoordinate() {
        return d_xCoordinate;
    }

    /**
     * Gets the y-coordinate of the country.
     * @return The y-coordinate of the country.
     */
    public String getYCoordinate() {
        return d_yCoordinate;
    }

    /**
     * Adds a neighboring country to the country.
     * @param p_country The neighboring country to add.
     */
    public void addNeighbor(Country p_country) {
        if (!(this.d_neighbors.containsKey(this))) {
            this.d_neighbors.put(this, new LinkedHashMap<>());
        }
        this.d_neighbors.get(this).put(p_country.d_name, p_country);
    }
    /**
     * Gets the neighboring countries of the country.
     * @return A LinkedHashMap containing the neighboring countries.
     */
    public LinkedHashMap<String, Country> getNeighbors() {
        this.d_neighbors.computeIfAbsent(this, k -> new LinkedHashMap<>());
        return this.d_neighbors.get(this);
    }

    /**
     * Checks if a neighboring country with the specified name exists.
     * @param p_name The name of the neighboring country to check.
     * @return true if a neighboring country with the specified name exists, false otherwise.
     */
    public boolean findNeighborByName(String p_name) {
        LinkedHashMap<String, Country> l_neighbors = d_neighbors.get(this);
        for (Country l_neighbor : l_neighbors.values()) {
            if (p_name.equals(l_neighbor.getName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a neighboring country with the specified ID.
     * @param p_id The ID of the neighboring country to remove.
     */
    public void removeNeighborById(int p_id) {
        LinkedHashMap<String, Country> l_neighbors = d_neighbors.get(this);
        l_neighbors.values().removeIf(neighbor -> p_id == neighbor.getId());
    }
}