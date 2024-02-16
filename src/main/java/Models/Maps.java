package Models;


import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.Paths;


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

            public void addContinent(String continentName, int continentValue) {
                String line  = continentName + " " + continentValue + " " + "#000";
                processContinentLine(line);
            }

            public void removeContinent(String p_continentName) {
                boolean continentFound = false;
                d_continents.values().forEach(l_continent -> {
                    if(l_continent.getName().equals(p_continentName)) {
                        d_continents.remove(l_continent.getId());
                        System.out.println("Removed '" + p_continentName + ".");
                        return;
                    }
                });
                if(!continentFound) {
                    System.out.println("Continent that you are trying to remove does not exit");
                }
            }
            
            

            public boolean continentAlreadyExists(String p_continentName) {
                for(Map.Entry<String, Continent> mapEntry : d_continents.entrySet()) {
                    Continent continent = mapEntry.getValue();
                    if(continent.getName().equals(p_continentName)) {
                        return true;
                    }
                }
                return false;
            }

    
            public void editContinent(String p_operation, String p_continentName, int p_continentValue) {
                if (p_continentName == null) {
                    System.out.println("Error: Continent name cannot be null.");
                    return;
                }
            
                if(!d_continents.isEmpty() && continentAlreadyExists(p_continentName)) {
                    System.out.println("The continent which you are trying to add already exist!");
                    return;
                }

                switch (p_operation) {
                    case "add":
                        addContinent(p_continentName, p_continentValue);
                        break;

                    case "remove":
                        removeContinent(p_continentName);
                        break;
                
                    default:
                        System.out.println("Invalid operation: " + p_operation);
                        break;
                }  
                
                
        }
    }