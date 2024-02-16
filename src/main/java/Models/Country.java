package Models;

import java.util.LinkedHashMap;

public class Country {
    private int d_id;
    private String d_name;
    private String d_continentId;
    private int d_armies;
    private String d_xCoordinate;
    private String d_yCoordinate;
    private String d_ownerName;
    private LinkedHashMap<Country, LinkedHashMap<String, Country>> d_neighbors;

    public Country(int p_id, String p_name) {
        this.d_id = p_id;
        this.d_name = p_name;
    }
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


    public int getId() {
        return d_id;
    }

    public void setId(int p_id) {
        this.d_id = p_id;
    }

    public String getName() {
        return d_name;
    }

    public void setName(String p_name) {
        this.d_name = p_name;
    }


    public int getArmies(){
        return this.d_armies;
    }

    public void setArmies(int p_armies){
         this.d_armies=p_armies;
    }

    public String getContinentId() {
        return d_continentId;
    }

    public String getXCoordinate() {
        return d_xCoordinate;
    }
    public String getYCoordinate() {
        return d_yCoordinate;
    }
    public void addNeighbor(Country p_country) {
        if (!(this.d_neighbors.containsKey(this))) {
            this.d_neighbors.put(this, new LinkedHashMap<>());
        }
        this.d_neighbors.get(this).put(p_country.d_name, p_country);
    }

    public LinkedHashMap<String, Country> getNeighbors() {
        return d_neighbors.get(this);
    }
}