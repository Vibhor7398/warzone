package Adapter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import Models.Continent;
import Models.Country;
import Models.Maps;

public class DominationMapIO {

    private String findContinentIdByName(Maps p_gameMap,String p_continentName) {
        for (Continent l_continent : p_gameMap.getContinents().values()) {
            if (String.valueOf(l_continent.getName()).equals(p_continentName)) {
                return String.valueOf(l_continent.getId());
            }
        }
        return "-1";
    }

    private Country findCountryById(Maps p_gameMap,String p_id) {
        for (Country l_country : p_gameMap.getCountries().values()) {
            if (String.valueOf(l_country.getId()).equals(p_id)) {
                return l_country;
            }
        }
        return null;
    }

    public void loadMap(Maps p_gameMap, String p_fileName) throws IOException {
        p_gameMap.resetMap();
        String l_content = Files.readString(Paths.get(p_fileName));
        String[] l_lines = l_content.split("\n");
        boolean l_readingContinents = false, l_readingCountries = false,l_readingBorders=false;
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
                Continent l_continent = new Continent(p_gameMap.getContinents().size() + 1, l_parts[0], Integer.parseInt(l_parts[1]),"gray");
                p_gameMap.getContinents().put(String.valueOf(p_gameMap.getContinents().size() + 1), l_continent);
            }
            if(l_readingCountries){
                String[] l_parts = l_line.trim().split(",");
                System.out.println(l_parts);
                int l_countryId=p_gameMap.getCountries().size() + 1;
                Country l_country = new Country(l_countryId,l_parts[0],findContinentIdByName(p_gameMap,l_parts[3]), l_parts[1], l_parts[2]);
                p_gameMap.getCountries().put(l_parts[0], l_country);
                String l_continent=l_parts[3].trim();
                System.out.println(p_gameMap.getContinents());
                String l_continentKey = null;
                Set<String> keys = p_gameMap.getContinents().keySet();

                for (String key : keys) {
                    if(p_gameMap.getContinents().get(key).getName().equals(l_continent)) {
                        l_continentKey = key;
                    }
                    System.out.println(key + " -- "
                            + p_gameMap.getContinents().get(key).getName());
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
            if(l_readingBorders){
                String[] l_parts = l_line.split(",");
                if (l_parts.length < 4) return;
                String l_countryId = l_parts[0].trim();
                Country l_country = findCountryById(p_gameMap,l_countryId);
                if (l_country == null) {
                    return;
                }
                for (int i = 4; i < l_parts.length; i++) {
                    String l_neighborId = l_parts[i].trim();
                    Country l_neighbor = findCountryById(p_gameMap,l_neighborId);
                    if (l_neighbor != null) {
                        l_country.addNeighbor(l_neighbor);
                    }
                }
            }

        }

    }
    public  void saveMap(Maps p_gameMap, String p_fileName) throws IOException {
    }

}
