package Adapter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import Models.Continent;
import Models.Country;
import Models.Maps;

/**
 * The DominationMapIO class provides methods to load and save maps in the Domination map format.
 * It contains methods to load a map from a file and save a map to a file.
 */
public class DominationMapIO {

    /**
     * Finds the continent ID by its name in the provided game map.
     *
     * @param p_gameMap      the game map
     * @param p_continentName the name of the continent
     * @return the ID of the continent if found, otherwise returns "-1"
     */
    private String findContinentIdByName(Maps p_gameMap, String p_continentName) {
        for (Continent l_continent : p_gameMap.getContinents().values()) {
            if (String.valueOf(l_continent.getName()).equals(p_continentName)) {
                return String.valueOf(l_continent.getId());
            }
        }
        return "-1";
    }

    /**
     * Finds a country by its name in the provided game map.
     *
     * @param p_gameMap the game map
     * @param p_id      the ID of the country
     * @return the country object if found, otherwise returns null
     */
    private Country findCountryByName(Maps p_gameMap, String p_id) {
        for (Country l_country : p_gameMap.getCountries().values()) {
            if (String.valueOf(l_country.getName()).equals(p_id)) {
                return l_country;
            }
        }
        return null;
    }

    /**
     * Loads a map from the specified file and populates the provided game map.
     * The file should be in the Domination map format. It reads the continents,
     * countries, and their borders from the file and initializes the game map accordingly.
     *
     * @param p_gameMap  the game map to populate
     * @param p_fileName the name of the file containing the map
     * @return {@code true} if the map is loaded successfully, {@code false} otherwise
     */

    public boolean loadMap(Maps p_gameMap, String p_fileName) {
        try {
            p_gameMap.resetMap();
            String l_content = Files.readString(Paths.get(p_fileName));
            String[] l_lines = l_content.split("\n");
            boolean l_readingContinents = false, l_readingCountries = false, l_readingBorders = false;
            for (String l_line : l_lines) {
                l_line = l_line.trim();
                switch (l_line) {
                    case "[Continents]" -> {
                        l_readingContinents = true;
                        l_readingCountries = false;
                        continue;
                    }
                    case "[Territories]" -> {
                        l_readingCountries = true;
                        l_readingContinents = false;
                        continue;
                    }
                }
                if (l_line.isEmpty() || (!(l_readingContinents || l_readingCountries))) {
                    continue;
                }
                if (l_readingContinents) {
                    String[] l_parts = l_line.split("=");
                    Continent l_continent = new Continent(p_gameMap.getContinents().size() + 1, l_parts[0], Integer.parseInt(l_parts[1]), "gray");
                    p_gameMap.getContinents().put(String.valueOf(p_gameMap.getContinents().size() + 1), l_continent);
                }
                if (l_readingCountries) {
                    String[] l_parts = l_line.trim().split(",");
                    int l_countryId = p_gameMap.getCountries().size() + 1;
                    Country l_country = new Country(l_countryId, l_parts[0], findContinentIdByName(p_gameMap, l_parts[3]), l_parts[1], l_parts[2]);
                    p_gameMap.getCountries().put(l_parts[0], l_country);
                    String l_continent = l_parts[3].trim();
                    String l_continentKey = null;
                    Set<String> keys = p_gameMap.getContinents().keySet();

                    for (String key : keys) {
                        if (p_gameMap.getContinents().get(key).getName().equals(l_continent)) {
                            l_continentKey = key;
                        }
                    }
                    p_gameMap.getContinents().get(l_continentKey).addCountry(l_country);
                }
            }

            for (String l_line : l_lines) {
                l_line = l_line.trim();
                if (l_line.equals("[Territories]")) {
                    l_readingBorders = true;
                    continue;
                }
                if (l_line.isEmpty() || (!(l_readingContinents || l_readingCountries))) {
                    continue;
                }
                if (l_readingBorders) {
                    String[] l_parts = l_line.split(",");
                    if (l_parts.length < 4) {
                        return false;
                    }
                    ;
                    String l_countryId = l_parts[0].trim();
                    Country l_country = findCountryByName(p_gameMap, l_countryId);
                    if (l_country == null) {
                        return false;
                    }
                    for (int i = 4; i < l_parts.length; i++) {
                        String l_neighborId = l_parts[i].trim();
                        Country l_neighbor = findCountryByName(p_gameMap, l_neighborId);
                        if (l_neighbor != null) {
                            l_country.addNeighbor(l_neighbor);
                        }
                    }
                }

            }
            return true;
        } catch (IOException e) {
            System.out.println("Map loading failed");
            return false;
        }

    }


    /**
     * Saves the provided game map to the specified file in the Domination map format.
     * The method writes the continents, countries, and their borders to the file.
     *
     * @param p_gameMap  the game map to save
     * @param p_fileName the name of the file to save the map
     * @return {@code true} if the map is saved successfully, {@code false} otherwise
     */
    public boolean saveMap(Maps p_gameMap, String p_fileName) {
        try {
            File l_file = new File(p_fileName);
            if (!l_file.exists()) {
                System.out.println("The file doesn't exist, creating a new file.");
                if (!l_file.createNewFile()) {
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
            l_contentBuilder.append("[Continents]\n");
            p_gameMap.getContinents().values().forEach(continent ->
                    l_contentBuilder.append(continent.getName()).append("=")
                            .append(continent.getContinentValue()).append("\n"));
            l_contentBuilder.append("\n");
            l_contentBuilder.append("[Territories]\n");
            Map<String, List<Country>> countriesByContinent = new HashMap<>();
            p_gameMap.getCountries().values().forEach(country -> {
                String continentName = p_gameMap.getContinents().get(country.getContinentId()).getName();
                countriesByContinent.computeIfAbsent(continentName, k -> new ArrayList<>()).add(country);
            });
            for (Continent continent : p_gameMap.getContinents().values()) {
                String continentName = continent.getName();
                List<Country> countries = countriesByContinent.getOrDefault(continentName, new ArrayList<>());
                countries.forEach(country -> {
                    l_contentBuilder.append(country.getName()).append(",").append(country.getXCoordinate()).append(",").append(country.getYCoordinate())
                            .append(",").append(continentName);
                    country.getNeighbors().values().forEach(neighbor ->
                            l_contentBuilder.append(",").append(neighbor.getName()));
                    l_contentBuilder.append("\n");
                });
                l_contentBuilder.append("\n");
            }
            Files.writeString(Paths.get(l_file.getPath()), l_contentBuilder.toString());
            return true;

        } catch (IOException e) {
            return false;
        }
    }
}

