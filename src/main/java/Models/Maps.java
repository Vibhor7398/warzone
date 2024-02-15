package Models;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


public class Maps {
    private LinkedHashMap<String, Continent> d_continents;
    private LinkedHashMap<String, Country> d_countries;
    private boolean d_isMapValid;
    private boolean d_mapFileLoaded;
    private String d_currentFileName;


    public Maps() {
        this.d_continents = new LinkedHashMap<>();
        this.d_countries = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Continent> getContinents() {
        return d_continents;
    }

    public LinkedHashMap<String, Country> getCountries() {
        return d_countries;
    }

    private Country findCountryById(String id) {
        for (Country country : d_countries.values()) {
            if (String.valueOf(country.getId()).equals(id)) {
                return country;
            }
        }
        return null;
    }
    private void processContinentLine(String line) {
        String[] parts = line.split(" ");
        Continent continent = new Continent(d_continents.size() + 1, parts[0], Integer.parseInt(parts[1]), parts[2]);
        d_continents.put(String.valueOf(d_continents.size()+1), continent);
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
            // Handle the case where the country isn't found if necessary
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

    public void validateMap() {
        this.d_isMapValid = validateFullConnectivity(this.d_countries) &&
                validateContinentExistence() &&
                validateNeighborExistence() &&
                validateContinentConnectivity();
    }

    public void traverseMap(Country country, Map<String, Boolean> visited) {
        visited.put(country.getName(), true);
        country.getNeighbors().forEach((name, neighbor) -> {
            if (Boolean.FALSE.equals(visited.get(name))) {
                traverseMap(neighbor, visited);
            }
        });
    }

    public boolean validateContinentExistence() {
        return this.d_countries.values().stream()
                .allMatch(country -> this.d_continents.containsKey(country.getContinentId()));
    }

    public boolean validateNeighborExistence() {
        return this.d_countries.values().stream()
                .flatMap(country -> country.getNeighbors().values().stream())
                .allMatch(neighbor -> this.d_countries.containsKey(neighbor.getName()));
    }

    public boolean validateContinentConnectivity() {
        return this.d_continents.values().stream()
                .allMatch(continent -> validateFullConnectivity(continent.getCountries()));
    }

    public boolean validateFullConnectivity(Map<String, Country> countries) {
        if (countries.isEmpty()) return true;

        LinkedHashMap<String, Boolean> visited = new LinkedHashMap<>();
        countries.values().forEach(country -> visited.put(country.getName(), false));

        Country startingCountry = countries.values().iterator().next(); // Starting from the first country
        traverseMap(startingCountry, visited);

        return visited.values().stream().allMatch(Boolean.TRUE::equals);
    }








}