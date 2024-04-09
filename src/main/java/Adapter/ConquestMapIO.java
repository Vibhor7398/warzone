package Adapter;

import Models.Continent;
import Models.Country;
import Models.Maps;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.stream.Collectors;


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
     * Loads a Conquest mode map from the specified file into the provided Maps object.
     *
     * @param p_gameMap The Maps object to populate with the loaded map data.
     * @param p_fileName The name of the file containing the map data.
     */
    public void loadMap(Maps p_gameMap, String p_fileName) {
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

                // Skip empty lines or lines outside of any known section
                if (l_line.isEmpty() || (!(l_readingContinents || l_readingCountries || l_readingBorders))) {
                    continue;
                }

                // Process the line based on the current section being read
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
        }catch(IOException e){
            System.out.println("Map loading failed");
            return;
        }
    }

    /**
     * Saves the current state of the game map, represented by the provided Maps object, to a file.
     *
     * @param p_gameMap The Maps object containing the map data to be saved.
     * @param p_fileName The name of the file to which the map data will be saved.
     */
    public void saveMap(Maps p_gameMap, String p_fileName) {
        try {
            File l_file = new File(p_fileName);
            if (!l_file.exists()) {
                System.out.println("The file doesn't exist, creating a new file.");
                if (!l_file.createNewFile()) {
                    return;
                }
            }
            StringBuilder l_contentBuilder = new StringBuilder();

            // Adding the file section
            String l_baseName = l_file.getName().split("\\.")[0];
            String line = String.format("pic %s_pic.jpg\nmap %s_map.gif\ncrd %s.cards\n\n", l_baseName, l_baseName, l_baseName);
            l_contentBuilder.append("[files]\n").append(line);

            // Adding the continent
            String l_continents = p_gameMap.getContinents().values().stream()
                    .sorted(Comparator.comparingInt(Continent::getId))
                    .map(l_continent -> String.format("%s %d %s\n", l_continent.getName(), l_continent.getContinentValue(), l_continent.getColor()))
                    .collect(Collectors.joining());
            l_contentBuilder.append("[continents]\n").append(l_continents).append("\n");

            // Adding the country
            String l_countries = p_gameMap.getCountries().values().stream()
                    .sorted(Comparator.comparingInt(Country::getId))
                    .map(l_country -> String.format("%d %s %s %s %s\n", l_country.getId(), l_country.getName(), l_country.getContinentId(), l_country.getXCoordinate(), l_country.getYCoordinate()))
                    .collect(Collectors.joining());
            l_contentBuilder.append("[countries]\n").append(l_countries).append("\n");

            // Adding the neighbours
            String l_borders = p_gameMap.getCountries().values().stream()
                    .map(country -> {
                        String l_neighbors = country.getNeighbors().values().stream()
                                .map(neighbor -> String.valueOf(neighbor.getId()))
                                .collect(Collectors.joining(" "));
                        return country.getId() + " " + l_neighbors + "\n";
                    })
                    .collect(Collectors.joining());

            l_contentBuilder.append("[borders]\n").append(l_borders);
            Files.writeString(Paths.get(l_file.getPath()), l_contentBuilder.toString(), StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println("Saving Map failed");
            return;
        }
    }

}
