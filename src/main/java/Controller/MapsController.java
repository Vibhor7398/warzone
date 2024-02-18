package Controller;
import Models.Maps;
import Models.Continent;
import Models.Country;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MapsController {
    private Maps maps;
    private LinkedHashMap<String, Continent> d_continents;
    private LinkedHashMap<String, Country> d_countries;

    public MapsController() {
        this.maps = new Maps();
        this.d_continents = this.maps.getContinents();
        this.d_countries = this.maps.getCountries();
    }


    public boolean isMapValid() {
        return this.maps.getMapValid();
    }
    public LinkedHashMap<String,Continent> getContinents(){
        return this.d_continents;
    }
    public boolean continentAlreadyExists(String p_continentName) {
        for (Map.Entry<String, Continent> mapEntry : d_continents.entrySet()) {
            Continent continent = mapEntry.getValue();
            if (continent.getName().equals(p_continentName)) {
                return true;
            }
        }
        return false;
    }

    public boolean continentAlreadyExists(int p_continentId) {
        for (Map.Entry<String, Continent> mapEntry : d_continents.entrySet()) {
            Continent continent = mapEntry.getValue();
            if (continent.getId() == p_continentId) {
                return true;
            }
        }
        return false;
    }

    private Country findCountryById(String id) {
        for (Country country : maps.getCountries().values()) {
            if (String.valueOf(country.getId()).equals(id)) {
                return country;
            }
        }
        return null;
    }

    private void processContinentLine(String line) {
        String[] parts = line.split(" ");
        Continent continent = new Continent(d_continents.size() + 1, parts[0], Integer.parseInt(parts[1]), parts[2]);
        d_continents.put(String.valueOf(d_continents.size() + 1), continent);
    }

    private void processCountryLine(String line) {
        String[] parts = line.split(" ");
        Country country = new Country(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3], parts[4]);
        d_countries.put(parts[1], country);
        //TL:DR Add a validation before adding country to the continent; check if the continent exists or not
        d_continents.get(parts[2].trim()).addCountry(country);
    }

    private void processBorderLine(String line) {
        String[] parts = line.split(" ");
        if (parts.length < 2) return;
        String countryId = parts[0].trim();
        Country country = findCountryById(countryId);
        if (country == null) {
            //TODO : Handle the case where the country isn't found if necessary
            return;
        }
        for (int i = 1; i < parts.length; i++) {
            String neighborId = parts[i].trim();
            Country neighbor = findCountryById(neighborId);
            if (neighbor != null) {
                country.addNeighbor(neighbor);
            }
        }
    }

    public void addContinent(String continentName, int continentValue) {
        if (!maps.getContinents().isEmpty() && continentAlreadyExists(continentName)) {
            System.out.println("The continent(" + continentName + ")you are trying to add already exist!");
            return;
        }
        String line = continentName + " " + continentValue + " " + "green";
        processContinentLine(line);
    }

    public void removeContinent(String p_continentName) {
        boolean continentFound = false;
        Iterator<Continent> iterator = d_continents.values().iterator();
        while (iterator.hasNext()) {
            Continent l_current_continent = iterator.next();
            if (l_current_continent.getName().equals(p_continentName)) {
                continentFound = true;
                iterator.remove();
                System.out.println("Removed '" + p_continentName + "'.");
                return;
            }
        }
        if (!continentFound) {
            System.out.println("Continent that you are trying to remove does not exit");
        }
    }

    public void validateMap() {
        maps.setMapValid(validateExistence() && validateConnectivity());
    }

    public boolean validateExistence() {
        boolean continentsExist = this.d_countries.values().stream()
                .allMatch(country -> this.d_continents.containsKey(country.getContinentId()));
        if (!continentsExist) return false;
        return this.d_countries.values().stream()
                .flatMap(country -> country.getNeighbors().values().stream())
                .allMatch(neighbor -> this.d_countries.containsKey(neighbor.getName()));
    }

    public boolean validateFullConnectivity(Map<String, Country> countries) {
        if (countries.isEmpty()) return true;
        LinkedHashMap<String, Boolean> visited = new LinkedHashMap<>();
        countries.values().forEach(country -> visited.put(country.getName(), false));
        traverseMap(countries.values().iterator().next(), visited);
        return visited.values().stream().allMatch(Boolean.TRUE::equals);
    }

    public boolean validateConnectivity() {
        for (Continent continent : this.d_continents.values()) {
            if (!validateFullConnectivity(continent.getCountries())) return false;
        }
        return this.d_countries.isEmpty() || validateFullConnectivity(this.d_countries);
    }

    public void traverseMap(Country country, Map<String, Boolean> visited) {
        visited.put(country.getName(), true);
        country.getNeighbors().forEach((name, neighbor) -> {
            if (Boolean.FALSE.equals(visited.get(name))) {
                traverseMap(neighbor, visited);
            }
        });
    }

    public void addCountry(String p_countryName, String p_continentName) {
        int continentId = -1;
        for (Continent continent : d_continents.values()) {
            if (continent.getName().equals(p_continentName)) {
                continentId = continent.getId();
                break;
            }
        }
        
        if(!d_continents.isEmpty() && !continentAlreadyExists(p_continentName)) {
            System.out.println(p_continentName + "Continent does not exist!");
            return;
        }
        int id = d_countries.size() + 1;
        String line  = id + " " + p_countryName + " " + continentId + " " + 0 + " " + 0;
        processCountryLine(line);
        System.out.println("Added successfully " + p_countryName);
    }

    public void removeCountry(String p_countryName) {
        boolean countryRemoved = false;
        Iterator<Country> iterator = d_countries.values().iterator();
        while (iterator.hasNext()) {
            Country l_country = iterator.next();
            if (l_country.getName().equals(p_countryName)) {
                countryRemoved = true;
                iterator.remove();
                d_continents.get(l_country.getContinentId()).removeCountry(p_countryName);
                System.out.println("Removed '" + p_countryName + "'.");
                return;
            }
        }
        if(!countryRemoved) {
            System.out.println("Country that you are trying to remove does not exit");
        }
    }


    public void loadMap(String p_file) throws IOException {
        String content = Files.readString(Paths.get(p_file));
        String[] lines = content.split("\n");
        boolean readingContinents = false, readingCountries = false, readingBorders = false;
        for (String line : lines) {
            line = line.trim();
            switch (line) {
                case "[continents]" -> {
                    readingContinents = true;
                    readingCountries = readingBorders = false;
                    continue;
                }
                case "[countries]" -> {
                    readingCountries = true;
                    readingContinents = readingBorders = false;
                    continue;
                }
                case "[borders]" -> {
                    readingBorders = true;
                    readingContinents = readingCountries = false;
                    continue;
                }
            }
            if (line.isEmpty() || (!(readingContinents || readingCountries || readingBorders))) {
                continue;
            }
            if (readingContinents) {
                processContinentLine(line);
            }
            if (readingCountries) {
                processCountryLine(line);
            }
            if (readingBorders) {
                processBorderLine(line);
            }
        }
    }

    public void showMap() {
        d_continents.values().forEach(p_continent -> {
            System.out.println("ID: " + p_continent.getId() + " | Name: " + p_continent.getName() +
                    " | Control Value: " + p_continent.getContinentValue() +
                    " | Number of Countries: " + p_continent.getCountries().size());
            p_continent.getCountries().forEach((countryId, country) -> {
                System.out.println("\tCountry ID: " + country.getId() + " | Name: " + country.getName());
                LinkedHashMap<String, Country> neighborsMap = country.getNeighbors();
                if (!neighborsMap.isEmpty()) {
                    String neighbors = String.join(", ", neighborsMap.keySet());
                    System.out.println("\t\tNeighbors: " + neighbors);
                } else {
                    System.out.println("\t\tNeighbors: None");
                }
            });
            System.out.println();
        });
    }

    private Country findCountryByName(String name) {
        for (Country country : d_countries.values()) {
            if (String.valueOf(country.getName()).equals(name)) {
                return country;
            }
        }
        return null;
    }

    public void editNeighbors(String p_operation, String countryName, String neighborName) {
        Country country = findCountryByName(countryName);
        if (country == null) {
            System.out.println("Country not found");
            return;
        }
        Optional<Country> neighborOptional = country.getNeighborsByName(neighborName);
        if (neighborOptional.isPresent()) {
            Country neighbor = neighborOptional.get();
            if ("add".equals(p_operation)) {
                System.out.println("The neigbor already exists");
            }
            if ("remove".equals(p_operation)) {
                country.removeNeighborById(neighbor.getId());
            }
        } else {
            // Neighbor not found
            if ("add".equals(p_operation)) {
                country.addNeighbor(neighborName);
            }
            if ("remove".equals(p_operation)) {
                System.out.println("Neighbor not found.");

            }
            return;
        }
    }

    public void saveMap(File p_file) throws IOException {
        //TODO 1 :  fix neighbor in savemap

        if (!p_file.exists()) {
            System.out.println("The file doesn't exist, creating a new file.");
            p_file.createNewFile();
        }
        StringBuilder contentBuilder = new StringBuilder();

        // Adding the file section
        String baseName = p_file.getName().split("\\.")[0];
        String line = String.format("pic %s_pic.jpg\nmap %s_map.gif\ncrd %s.cards\n\n", baseName, baseName, baseName);
        contentBuilder.append("[files]\n").append(line);

        // Adding the continent
        String continents = d_continents.values().stream()
                .sorted(Comparator.comparingInt(Continent::getId))
                .map(continent -> String.format("%s %d %s\n", continent.getName(), continent.getContinentValue(), continent.getColor()))
                .collect(Collectors.joining());
        contentBuilder.append("[continents]\n").append(continents).append("\n");

        // Adding the country
        String countries = d_countries.values().stream()
                .sorted(Comparator.comparingInt(Country::getId))
                .map(country -> String.format("%d %s %s %s %s\n", country.getId(), country.getName(), country.getContinentId(), country.getXCoordinate(), country.getYCoordinate()))
                .collect(Collectors.joining());
        contentBuilder.append("[countries]\n").append(countries).append("\n");

        // Adding the neighbours
        String borders = d_countries.values().stream()
                .map(country -> {
                    String neighbors = country.getNeighbors().values().stream()
                            .map(neighbor -> String.valueOf(neighbor.getId()))
                            .collect(Collectors.joining(" "));
                    return country.getId() + " " + neighbors + "\n";
                })
                .collect(Collectors.joining());
        contentBuilder.append("[borders]\n").append(borders);
        Files.write(Paths.get(p_file.getPath()), contentBuilder.toString().getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE);
    }
}