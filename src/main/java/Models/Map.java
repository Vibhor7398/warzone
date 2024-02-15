package Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Map {
    private LinkedHashMap<String,Continent> d_continents;
    private LinkedHashMap<String,Country> d_countries;

    public Map(){
        this.d_continents=new LinkedHashMap<>();
        this.d_countries=new LinkedHashMap<>();
    }
    public LinkedHashMap<String, Continent> getContinents() {
        return d_continents;
    }
    public LinkedHashMap<String, Country> getCountries() {
        return d_countries;
    }
    public void loadMap(File p_file) throws IOException{
        this.d_continents=new LinkedHashMap<>();
        this.d_countries=new LinkedHashMap<>();
        // Created temp variables
        Iterator<String> l_borderStream;
        Iterator<String> l_stream;
        String[] l_mapLines;
        String l_tempLine;
        String[] l_tempData;

        // Reading the file stream
        FileInputStream d_inputStream=new FileInputStream(p_file);
        byte[] d_fileData=new byte[(int) p_file.length()];
        d_inputStream.read(d_fileData);
        d_inputStream.close();
        String l_finalMap=new String(d_fileData, StandardCharsets.UTF_8);
        l_finalMap+="\n";

        // Converting the file data into array stream
        l_mapLines=l_finalMap.split("\n");
        l_stream= Arrays.stream(l_mapLines).iterator();
        // Check until stream has no values;
        while(l_stream.hasNext()){
            l_tempLine = l_stream.next().trim();
            // Ignore until [continents] is found
            // Once found, load the continents
            if(l_tempLine.equals("[continents]")){
                l_tempLine=l_stream.next().trim();
                // Check until next [ is found as the next [ wil be of country or empty lines
                while(!(l_tempLine.startsWith("["))){
                    // Found a empty string, break the loop
                    if(l_tempLine.isEmpty())
                        break;
                    // Split the line using spaces
                    l_tempData=l_tempLine.split(" ");
                    // Create a new continent
                    Continent continent=new Continent(d_continents.size() + 1,
                            l_tempData[0].trim(), Integer.parseInt(l_tempData[1].trim()), l_tempData[2].trim());
                    // Put it in the local variable
                    d_continents.put(l_tempData[0].trim(), continent);
                    l_tempLine = l_stream.next().trim();
                }
            }

            // Ignore until [countries] is found
            // Once found, load the continents
            if(l_tempLine.equals("[countries]")){
                // To use variable in the loop, either make it final or a single string array
                String[] l_continentName=new String[1];
                l_tempLine=l_stream.next().trim();
                // Check until next [ is found as the next [ wil be of borders or empty lines
                while (!(l_tempLine.startsWith("[")) && !(l_tempLine.isEmpty())) {
                    // Split the line using spaces
                    l_tempData = l_tempLine.split(" ");
                    // Parse continentId into string as the 3rd data on the country map is continent
                    int l_continentId = Integer.parseInt(l_tempData[2].trim());
                    // Get the continent name of the matched continent id
                    this.d_continents.values().stream().parallel().forEach(p_continent -> {
                        if (p_continent.getId() == l_continentId) {
                            l_continentName[0] = p_continent.getName();

                        }
                    });
                    // Create a country object with rest of the fields; we are storing colors for the sake of loading map but we will not be using it.
                    Country country=new Country(Integer.parseInt(l_tempData[0].trim()),
                            l_tempData[1].trim(), l_continentName[0], l_tempData[3], l_tempData[4]);
                    // Save the countries in temp variable
                    d_countries.put(l_tempData[1].trim(),country);
                    // Save the countries in continents too
                    this.d_continents.get(l_continentName[0]).addCountry(this.d_countries.get(l_tempData[1].trim()));
                    l_tempLine = l_stream.next().trim();
                }
            }


            if (l_tempLine.equals("[borders]")) {
                l_tempLine = l_stream.next().trim();
                while (!(l_tempLine.startsWith("[")) && !(l_tempLine.isEmpty()) && l_stream.hasNext()) {
                    l_tempData = l_tempLine.split(" ");
                    l_borderStream = Arrays.stream(l_tempData).iterator();
                    String[] l_tempCountryPair = {null, null};
                    int l_firstCountryId = Integer.parseInt(l_borderStream.next().trim());
                    // Fetch name based on country id
                    this.d_countries.values().forEach(p_country -> {
                        if (p_country.getId() == l_firstCountryId)
                            l_tempCountryPair[0] = p_country.getName();
                    });

                    // Fetch 2nd country id and add it in a pair
                    while (l_borderStream.hasNext()) {
                        int l_secondCountryId = Integer.parseInt(l_borderStream.next().trim());
                        this.d_countries.values().forEach(p_country -> {
                            if (p_country.getId() == l_secondCountryId)
                                l_tempCountryPair[1] = p_country.getName();
                        });

                        // if only both countries exist, add them as neighbors
                        if (l_tempCountryPair[0] != null && l_tempCountryPair[1] != null)
                            this.d_countries.get(l_tempCountryPair[0]).addNeighbor(this.d_countries.get(l_tempCountryPair[1]));
                    }
                    l_tempLine = l_stream.next().trim();
                }
            }
        }












    }

}
