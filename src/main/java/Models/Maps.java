package Models;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.nio.file.StandardOpenOption;
import java.util.stream.Collectors;

public class Maps {
    private LinkedHashMap<String, Continent> d_continents;
    private LinkedHashMap<String, Country> d_countries;
    private boolean d_isMapValid;
    private boolean d_mapFileLoaded;

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
    private Country findCountryByName(String name) {
        for (Country country : d_countries.values()) {
            if (String.valueOf(country.getName()).equals(name)) {
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

    public boolean isMapValid(){
        return this.d_isMapValid;
    }
            public void addContinent(String continentName, int continentValue) {
                if(!d_continents.isEmpty() && continentAlreadyExists(continentName)) {
                    System.out.println("The continent(" + continentName + ")you are trying to add already exist!");
                    return;
                }
                String line  = continentName + " " + continentValue + " " + "#000";
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

            public boolean continentAlreadyExists(int p_continentId) {
                for(Map.Entry<String, Continent> mapEntry : d_continents.entrySet()) {
                    Continent continent = mapEntry.getValue();
                    if(continent.getId() == p_continentId) {
                        return true;
                    }
                }
                return false;
            }

    
         

    public void validateMap() {
        this.d_isMapValid = validateExistence() && validateConnectivity();
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

       public void editContinent(String p_operation, String p_continentName, int... p_continentValue) {
                int continentValue;
                if (p_continentValue.length > 0) {
                    continentValue = p_continentValue[0];
                } else {
                    continentValue = 0; 
                }
    
                if (p_continentName == null) {
                    System.out.println("Error: Continent name cannot be null.");
                    return;
                }
                if (p_operation == null) {
                    System.out.println("Error: Continent name cannot be null.");
                    return;
                }

                switch (p_operation) {
                    case "add":
                        addContinent(p_continentName, continentValue);
                        break;

                    case "remove":
                        removeContinent(p_continentName);
                        break;
                
                    default:
                        System.out.println("Invalid operation: " + p_operation);
                        break;
                }   
        }

        public void addCountry(String p_countryName, int p_continentId) {
            if(p_continentId != -1 && !d_continents.isEmpty() && !continentAlreadyExists(p_continentId)) {
                System.out.println(p_continentId + "Continent does not exist!");
                return;
            }
            int id = d_countries.size() + 1;
            String line  = id + " " + p_countryName + " " + p_continentId + " " + 0 + " " + 0;
            processCountryLine(line);
            System.out.println("Added successfully " + p_countryName);
        }

    public void editMap(String p_fileName) throws IOException {
        this.d_mapFileLoaded = true;
        loadMap(p_fileName);
    }
    public void saveMap(File p_file) throws IOException {
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
        

        public void editCountry(String p_operation, String p_countryName, OptionalInt p_continentId) {
            int l_continentId = p_continentId.orElse(-1);
            
            if (p_countryName == null) {
                System.out.println("Error: Country name cannot be null.");
                return;
            }

            switch (p_operation) {
                case "add":
                    addCountry(p_countryName, l_continentId);
                    break;

                case "remove":
                    removeCountry(p_countryName);
                    break;
            
                default:
                    System.out.println("Invalid operation: " + p_operation);
                    break;
            }   
        }
        
    public void editNeighbors(String p_operation, String countryName, String neighborName) {
        Country country = findCountryByName(countryName);
        // country does not exist
        if(country == null) {
            System.out.println("Country not found");
            return;
        }
        Optional<Country> neighborOptional = country.getNeighborsByName(neighborName);
        if (neighborOptional.isPresent()) {
            Country neighbor = neighborOptional.get();
            if("add".equals(p_operation)) {
                System.out.println("The neigbor already exists");
            } 
            if("remove".equals(p_operation)){
                country.removeNeighborById(neighbor.getId());
            }
        } else {
            // Neighbor not found
            if("add".equals(p_operation)) {
                country.addNeighbor(neighborName);
            } 
            if("remove".equals(p_operation)){
                System.out.println("Neighbor not found.");

            }
            return;
        }
        
    }
}