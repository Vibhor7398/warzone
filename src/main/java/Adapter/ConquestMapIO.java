package Adapter;

import Models.Continent;
import Models.Country;
import Models.Maps;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * The ConquestMapIO class provides functionality to load and save Conquest mode maps from/to files.
 * It implements ConquestMapInterface for map loading and saving operations.
 */
public class ConquestMapIO {

    /**
     * Processes a line containing continent data from the map file and updates the provided Maps object accordingly.
     *
     * @param p_gameMap The Maps object to be updated with the continent data.
     * @param p_line The line from the map file containing continent information.
     */
    private void processContinentLine(Maps p_gameMap,String p_line) {
        String[] l_parts = p_line.split(" ");
        Continent l_continent = new Continent(p_gameMap.getContinents().size() + 1, l_parts[0], Integer.parseInt(l_parts[1]), l_parts[2]);
        p_gameMap.getContinents().put(String.valueOf(p_gameMap.getContinents().size() + 1), l_continent);
    }


    /**
     * Processes a line containing country data from the map file and updates the provided Maps object accordingly.
     *
     * @param p_gameMap The Maps object to be updated with the country data.
     * @param p_line The line from the map file containing country information.
     */
    private void processCountryLine(Maps p_gameMap,String p_line) {
        String[] l_parts = p_line.split(" ");
        Country l_country = new Country(Integer.parseInt(l_parts[0]), l_parts[1], l_parts[2], l_parts[3], l_parts[4]);
        p_gameMap.getCountries().put(l_parts[1], l_country);
        String l_continent=l_parts[2].trim();
        if(!p_gameMap.getContinents().containsKey(l_continent)){
            return;
        }
        p_gameMap.getContinents().get(l_parts[2].trim()).addCountry(l_country);
    }

    /**
     * Processes a line containing border data from the map file and updates the provided Maps object accordingly.
     *
     * @param p_gameMap The Maps object to be updated with the border data.
     * @param p_line The line from the map file containing border information.
     */
    private void processBorderLine(Maps p_gameMap,String p_line) {
        String[] l_parts = p_line.split(" ");
        if (l_parts.length < 2) return;
        String l_countryId = l_parts[0].trim();
        Country l_country = findCountryById(p_gameMap,l_countryId);
        if (l_country == null) {
            return;
        }
        for (int i = 1; i < l_parts.length; i++) {
            String l_neighborId = l_parts[i].trim();
            Country l_neighbor = findCountryById(p_gameMap,l_neighborId);
            if (l_neighbor != null) {
                l_country.addNeighbor(l_neighbor);
            }
        }
    }

    /**
     * Finds a country in the provided Maps object based on its ID.
     *
     * @param p_gameMap The Maps object to search for the country.
     * @param p_id The ID of the country to find.
     * @return The Country object with the specified ID if found, null otherwise.
     */
    private Country findCountryById(Maps p_gameMap,String p_id) {
        for (Country l_country : p_gameMap.getCountries().values()) {
            if (String.valueOf(l_country.getId()).equals(p_id)) {
                return l_country;
            }
        }
        return null;
    }

    /**
     * Loads a game map from a file into the provided Maps object.
     *
     * @param p_gameMap The Maps object to load the game map into.
     * @param p_fileName The file name of the map file to load.
     * @return true if the map is loaded successfully, false otherwise.
     */
    public boolean loadMap(Maps p_gameMap, String p_fileName)  {
        try {
            p_gameMap.resetMap();
            String l_content = Files.readString(Paths.get(p_fileName));
            String[] l_lines = l_content.split("\n");
            boolean l_readingContinents = false, l_readingCountries = false, l_readingBorders = false;
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
                if (l_line.isEmpty() || (!(l_readingContinents || l_readingCountries || l_readingBorders))) {
                    continue;
                }
                if (l_readingContinents) {
                    processContinentLine(p_gameMap, l_line);
                }
                if (l_readingCountries) {
                    processCountryLine(p_gameMap, l_line);
                }
                if (l_readingBorders) {
                    processBorderLine(p_gameMap, l_line);
                }
            }
            return true;
        }catch(IOException e){
            System.out.println("Map is invalid!");
            return false;
        }
    }

    /**
     * Saves the game map to a file.
     *
     * @param p_gameMap The Maps object representing the game map.
     * @param p_fileName The name of the file to save the map to.
     * @return true if the map is successfully saved, false otherwise.
     */
    public boolean saveMap(Maps p_gameMap, String p_fileName) {
        try {
            File l_file = new File(p_fileName);
            if (!l_file.exists()) {
                System.out.println("The file doesn't exist, creating a new file.");
                if (!l_file.createNewFile()) {
                    System.out.println("Failed to create the file.");
                    return false;
                }
            }
            StringBuilder l_contentBuilder = new StringBuilder();
            l_contentBuilder.append("[Map]\n")
                    .append("author=Sean O'Connor\n")
                    .append("image=world.bmp\n")
                    .append("wrap=no\n")
                    .append("scroll=horizontal\n")
                    .append("warn=yes\n\n");
            List<String> continentOrder = List.of("NorthAmerica", "SouthAmerica", "Africa", "Europe", "Asia", "Australia");
            Map<String, Continent> sortedContinents = new LinkedHashMap<>();
            continentOrder.forEach(continentName -> {
                Continent continent = p_gameMap.getContinents().values().stream()
                        .filter(c -> c.getName().equals(continentName))
                        .findFirst().orElse(null);
                if (continent != null) {
                    sortedContinents.put(continentName, continent);
                }
            });
            l_contentBuilder.append("[Continents]\n");
            sortedContinents.forEach((name, continent) ->
                    l_contentBuilder.append(name).append("=")
                            .append(continent.getContinentValue()).append("\n"));
            l_contentBuilder.append("\n");
            Files.writeString(Paths.get(l_file.getPath()), l_contentBuilder.toString(), StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            System.out.println("Failed to save map!");
            return false;
        }
    }
}
