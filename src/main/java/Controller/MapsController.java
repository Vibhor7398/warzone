/**
 *
 * 
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Girmire, Inderjeet Singh, Md. Zaid
 * @version 1.0
 */

package Controller;

import Models.Maps;
import Models.Continent;
import Models.Country;
import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MapsController {
    private final Maps d_maps;
    private static LinkedHashMap<String, Continent> d_Continents;
    private static LinkedHashMap<String, Country> d_Countries;

    public MapsController() {
        this.d_maps = new Maps();
        d_Continents = this.d_maps.getContinents();
        d_Countries = this.d_maps.getCountries();
    }

    public LinkedHashMap<String, Country> getD_countries() {
        return d_Countries;
    }

    public boolean isMapValid() {
        return this.d_maps.getMapValid();
    }
    public static LinkedHashMap<String,Continent> getContinents(){
        return d_Continents;
    }
    public boolean continentAlreadyExists(String p_continentName) {
        for (Map.Entry<String, Continent> l_mapEntry : d_Continents.entrySet()) {
            Continent l_continent = l_mapEntry.getValue();
            if (l_continent.getName().equals(p_continentName)) {
                return true;
            }
        }
        return false;
    }

    private Country findCountryById(String p_id) {
        for (Country l_country : d_maps.getCountries().values()) {
            if (String.valueOf(l_country.getId()).equals(p_id)) {
                return l_country;
            }
        }
        return null;
    }

    public static Country getCountryByName(String p_countryName){
        for (Country l_country : d_Countries.values()) {
            if(l_country.getName().equals(p_countryName)){
                return l_country;
            }
        }
        return null;
    }

    private void processContinentLine(String p_line) {
        String[] l_parts = p_line.split(" ");
        Continent l_continent = new Continent(d_Continents.size() + 1, l_parts[0], Integer.parseInt(l_parts[1]), l_parts[2]);
        d_Continents.put(String.valueOf(d_Continents.size() + 1), l_continent);
    }

    private void processCountryLine(String p_line) {
        String[] l_parts = p_line.split(" ");
        Country l_country = new Country(Integer.parseInt(l_parts[0]), l_parts[1], l_parts[2], l_parts[3], l_parts[4]);
        d_Countries.put(l_parts[1], l_country);
        String l_continent=l_parts[2].trim();
        if(!d_Continents.containsKey(l_continent)){
            return;
        }
        d_Continents.get(l_parts[2].trim()).addCountry(l_country);
    }

    /**
     * Processes a line of border information from the map file.
     * The line is expected to contain a country ID followed by neighbor country IDs separated by spaces.
     * Each neighbor country ID found in the line is added as a neighbor to the corresponding country.
     *
     * @param p_line The line of border information to process.
     */
    private void processBorderLine(String p_line) {
        String[] l_parts = p_line.split(" ");
        if (l_parts.length < 2) return;
        String l_countryId = l_parts[0].trim();
        Country l_country = findCountryById(l_countryId);
        if (l_country == null) {
                return;
        }
        for (int i = 1; i < l_parts.length; i++) {
            String l_neighborId = l_parts[i].trim();
            Country l_neighbor = findCountryById(l_neighborId);
            if (l_neighbor != null) {
                l_country.addNeighbor(l_neighbor);
            }
        }
    }

    public void addContinent(String p_continentName, int p_continentValue) {
        if (!d_maps.getContinents().isEmpty() && continentAlreadyExists(p_continentName)) {
            System.out.println("The continent(" + p_continentName + ")you are trying to add already exist!");
            return;
        }
        String l_line = p_continentName + " " + p_continentValue + " " + "green";
        processContinentLine(l_line);
    }

    public void removeContinent(String p_continentName) {
        Iterator<Continent> l_iterator = d_Continents.values().iterator();
        while (l_iterator.hasNext()) {
            Continent l_currentContinent = l_iterator.next();
            if (l_currentContinent.getName().equals(p_continentName)) {
                l_iterator.remove();
                System.out.println("Removed '" + p_continentName + "'.");
                return;
            }
        }
        System.out.println("Continent that you are trying to remove does not exist!");
    }

    public void validateMap() {
        d_maps.setMapValid(validateExistence() && validateConnectivity());
    }

    public boolean validateExistence() {
        boolean l_continentsExist = d_Countries.values().stream()
                .allMatch(l_country -> d_Continents.containsKey(l_country.getContinentId()));
        if (!l_continentsExist) return false;
        boolean l_allContinentsHaveCountries = d_Continents.entrySet().stream()
                .noneMatch(l_entry -> l_entry.getValue().getCountries().isEmpty());
        if (!l_allContinentsHaveCountries) return false;
        return d_Countries.values().stream()
                .flatMap(country -> country.getNeighbors().values().stream())
                .allMatch(neighbor -> d_Countries.containsKey(neighbor.getName()));
    }

    public boolean validateFullConnectivity(Map<String, Country> p_countries) {
        if (p_countries.isEmpty()) return true;
        LinkedHashMap<String, Boolean> l_visited = new LinkedHashMap<>();
        p_countries.values().forEach(country -> l_visited.put(country.getName(), false));
        traverseMap(p_countries.values().iterator().next(), l_visited);
        return l_visited.values().stream().allMatch(Boolean.TRUE::equals);
    }

    public boolean validateConnectivity() {
        for (Continent l_continent : d_Continents.values()) {
            if (!validateFullConnectivity(l_continent.getCountries())) return false;
        }
        return d_Countries.isEmpty() || validateFullConnectivity(d_Countries);
    }

    public void traverseMap(Country p_country, Map<String, Boolean> p_visited) {
        p_visited.put(p_country.getName(), true);
        p_country.getNeighbors().forEach((name, neighbor) -> {
            if (Boolean.FALSE.equals(p_visited.get(name))) {
                traverseMap(neighbor, p_visited);
            }
        });
    }

    public boolean countryAlreadyExists(String p_country) {
        for (Map.Entry<String, Country> l_mapEntry : d_Countries.entrySet()) {
            Country l_country = l_mapEntry.getValue();
            if (l_country.getName().equals(p_country)) {
                return true;
            }
        }
        return false;
    }

    /**
    * Adds a new country to the map.
    * The country is assigned to the specified continent based on the continent name provided.
    * If the continent does not exist or the country already exists, the operation is aborted.
    * The new country is assigned an ID, and its information is processed to update the map.
    *
    * @param p_countryName   The name of the country to add.
    * @param p_continentName The name of the continent to which the country belongs.
    */
    public void addCountry(String p_countryName, String p_continentName) {
        // Initialize the continent ID to -1
        int l_continentId = -1;

        // Find the continent ID corresponding to the provided continent name
        for (Continent l_continent : d_Continents.values()) {
            if (l_continent.getName().equals(p_continentName)) {
                l_continentId = l_continent.getId();
                break;
            }
        }
        
        // Check if the continent exists
        if(!d_Continents.isEmpty() && !continentAlreadyExists(p_continentName)) {
            System.out.println(p_continentName + "Continent does not exist!");
            return;
        }

        // Check if the country already exists
        if(!d_Countries.isEmpty() && countryAlreadyExists(p_countryName)) {
            System.out.println(p_countryName + "Country already exists!");
            return;
        }
        // Generate a unique ID for the new country
        int l_id = d_Countries.size() + 1;

        // Create a line of information for the new country
        String l_line  = l_id + " " + p_countryName + " " + l_continentId + " " + 0 + " " + 0;
        
        // Process the country line to update the map
        processCountryLine(l_line);
        System.out.println("Added successfully " + p_countryName);
    }

    /**
    * Removes a country from the map based on the provided country name.
    * If the country is found, it is removed from the list of countries and its association with its continent is updated.
    * If the country does not exist, a message is printed indicating its absence.
    *
    * @param p_countryName The name of the country to remove.
    */
    public void removeCountry(String p_countryName) {
        // Get an iterator over the collection of countries
        Iterator<Country> l_iterator = d_Countries.values().iterator();
        
        // Iterate through the countries
        while (l_iterator.hasNext()) {
            Country l_country = l_iterator.next();

            // Check if the country name matches the provided country name
            if (l_country.getName().equals(p_countryName)) {
                // Remove the country from the list of countries
                l_iterator.remove();

                // Remove the country from its associated continent
                d_Continents.get(l_country.getContinentId()).removeCountry(p_countryName);
                System.out.println("Removed '" + p_countryName + "'.");
                return;
            }
        }
        // If the loop completes without finding the country, print a message indicating its absence
        System.out.println("Country that you are trying to remove does not exit");
    }

    public void editMap(File p_file) throws IOException{
        if (!p_file.exists()) {
            System.out.println("The file doesn't exist, creating a new file.");
           if(!p_file.createNewFile()){
               return;
           }
        }
        loadMap(p_file.getPath());
    }

    /**
    * Loads a map from the specified file and populates the map data structures accordingly.
    * The file is expected to have sections for continents, countries, and borders.
    * Each section is processed separately based on the section header.
    * Lines within each section are processed to extract relevant information and update the map.
    * 
    * @param p_file The path to the file containing the map data.
    * @throws IOException If an I/O error occurs while reading the file.
    */
    public void loadMap(String p_file) throws IOException {
        // Read the entire content of the file into a string
        String l_content = Files.readString(Paths.get(p_file));
        
        // Split the content into lines
        String[] l_lines = l_content.split("\n");
        
        // Flags to indicate the current section being read
        boolean l_readingContinents = false, l_readingCountries = false, l_readingBorders = false;

        // Iterate through each line in the file
        for (String l_line : l_lines) {
            l_line = l_line.trim();
            switch (l_line) {
                case "[continents]" -> {
                    l_readingContinents = true;
                    l_readingCountries = l_readingBorders = false;
                    continue;
                }
                case "[countries]" -> {
                    l_readingCountries = true;
                    l_readingContinents = l_readingBorders = false;
                    continue;
                }
                case "[borders]" -> {
                    l_readingBorders = true;
                    l_readingContinents = l_readingCountries = false;
                    continue;
                }
            }
            
            // Skip empty lines or lines outside of any known section
            if (l_line.isEmpty() || (!(l_readingContinents || l_readingCountries || l_readingBorders))) {
                continue;
            }
            
            // Process the line based on the current section being read
            if (l_readingContinents) {
                processContinentLine(l_line);
            }
            if (l_readingCountries) {
                processCountryLine(l_line);
            }
            if (l_readingBorders) {
                processBorderLine(l_line);
            }
        }
    }

    /**
     * Displays information about the current state of the map, including continents, countries, and their neighbors.
     * The method iterates over all continents and their associated countries, printing details such as ID, name, continent value,
     * number of countries, country ID, country name, armies, and neighbors.
     * If a country has neighbors, they are listed; otherwise, it is indicated that the country has no neighbors.
    */
    public void showMap() {
        d_Continents.values().forEach(p_continent -> {
            System.out.println("ID: " + p_continent.getId() + " | Name: " + p_continent.getName() +
                    " | Continent Value: " + p_continent.getContinentValue() +
                    " | Number of Countries: " + p_continent.getCountries().size());
            p_continent.getCountries().forEach((countryId, country) -> {
                System.out.println("\tCountry ID: " + country.getId() + " | Name: " + country.getName()+" | Armies: " + country.getArmies());
                LinkedHashMap<String, Country> l_neighborsMap = country.getNeighbors();
                if (!l_neighborsMap.isEmpty()) {
                    String l_neighbors = String.join(", ", l_neighborsMap.keySet());
                    System.out.println("\t\tNeighbors: " + l_neighbors);
                } else {
                    System.out.println("\t\tNeighbors: None");
                }
            });
            System.out.println();
        });
    }

    private Country findCountryByName(String p_name) {
        for (Country l_country : d_Countries.values()) {
            if (String.valueOf(l_country.getName()).equals(p_name)) {
                return l_country;
            }
        }
        return null;
    }

    /**
     * Edits the neighbors of a specified country based on the provided operation.
     * The method finds the country and its neighbor by name, verifies their existence,
     * and performs the requested operation: adding or removing the neighbor.
     * If the operation is successful, appropriate messages are printed.
     *
     * @param p_operation    The operation to perform: "add" to add a neighbor, "remove" to remove a neighbor.
     * @param p_countryName  The name of the country for which neighbors are being edited.
     * @param p_neighborName The name of the neighbor country to be added or removed.
    */
    public void editNeighbors(String p_operation, String p_countryName, String p_neighborName) {
        Country l_country = findCountryByName(p_countryName);
        Country l_neighbour=findCountryByName(p_neighborName);
        if(l_country==null){
            System.out.println("Country doesn't exists!");
            return;
        }
        if(l_neighbour==null){
            System.out.println("Neighbor country doesn't exists!");
            return;
        }

        if("add".equals(p_operation)) {
            if(l_country.findNeighborByName(p_neighborName)){
                System.out.println("Neighbor already exists!");
                return;
            }
            l_country.addNeighbor(l_neighbour);
        }

        if("remove".equals(p_operation)) {
            if(l_country.findNeighborByName(p_neighborName)){
                l_country.removeNeighborById(l_neighbour.getId());
                return;
            }
            System.out.println("The neighbor you are trying to remove doesn't exist");
        }
    }


    /**
     * Saves the current map data to a file.
     * The method writes the map details, including files, continents, countries, and their neighbors,
     * to the specified file in the format required for map files.
     *
     * @param p_file The file object representing the file to which the map data will be saved.
     * @throws IOException If an I/O error occurs while writing to the file.
    */
    public void saveMap(File p_file) throws IOException {
        if (!p_file.exists()) {
            System.out.println("The file doesn't exist, creating a new file.");
            if(!p_file.createNewFile()){
                return;
            }
        }
        StringBuilder l_contentBuilder = new StringBuilder();

        // Adding the file section
        String l_baseName = p_file.getName().split("\\.")[0];
        String line = String.format("pic %s_pic.jpg\nmap %s_map.gif\ncrd %s.cards\n\n", l_baseName, l_baseName, l_baseName);
        l_contentBuilder.append("[files]\n").append(line);

        // Adding the continent
        String l_continents = d_Continents.values().stream()
                .sorted(Comparator.comparingInt(Continent::getId))
                .map(l_continent -> String.format("%s %d %s\n", l_continent.getName(), l_continent.getContinentValue(), l_continent.getColor()))
                .collect(Collectors.joining());
        l_contentBuilder.append("[continents]\n").append(l_continents).append("\n");

        // Adding the country
        String l_countries = d_Countries.values().stream()
                .sorted(Comparator.comparingInt(Country::getId))
                .map(l_country -> String.format("%d %s %s %s %s\n", l_country.getId(), l_country.getName(), l_country.getContinentId(), l_country.getXCoordinate(), l_country.getYCoordinate()))
                .collect(Collectors.joining());
        l_contentBuilder.append("[countries]\n").append(l_countries).append("\n");

        // Adding the neighbours
        String l_borders = d_Countries.values().stream()
                .map(country -> {
                    String l_neighbors = country.getNeighbors().values().stream()
                            .map(neighbor -> String.valueOf(neighbor.getId()))
                            .collect(Collectors.joining(" "));
                    return country.getId() + " " + l_neighbors + "\n";
                })
                .collect(Collectors.joining());

        l_contentBuilder.append("[borders]\n").append(l_borders);
        Files.writeString(Paths.get(p_file.getPath()), l_contentBuilder.toString(), StandardOpenOption.WRITE);
    }
}