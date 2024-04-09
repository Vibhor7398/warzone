package Strategy;

import static org.junit.Assert.*;

import Models.Country;
import Models.Player;
import Orders.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestRandomStrategy {

    private Player d_player;
    private List<Country> d_countries;
    private RandomStrategy d_randomStrategy;

    @Before
    public void setUp() {
        d_player = new Player("Abhi");
        d_countries = new ArrayList<>();
        d_randomStrategy = new RandomStrategy(d_player, d_countries);
        Country l_new_country = new Country("Bermuda");
        l_new_country.setArmies(3);
        d_player.addCountryToCountriesOwned(l_new_country);
        d_countries.add(l_new_country);
        Country l_neighboutCountry = new Country("Bermuda");
        l_neighboutCountry.setArmies(7);
        l_new_country.addNeighbor(l_neighboutCountry);
        d_countries.add(l_neighboutCountry);
    }

    @Test
    public void testCreateOrder() {
        Order l_order;
        d_player.setArmies(0);
        l_order = d_randomStrategy.createOrder();
        assertTrue(l_order instanceof Advance || l_order == null || l_order instanceof Airlift || l_order instanceof Bomb ||l_order instanceof Blockade);
    }

    @Test
    public void testToAttack() {
        Country l_country = new Country("Country1");
        d_player.addCountryToCountriesOwned(l_country);
        d_countries.add(l_country);

        Country neighbor1 = new Country("Neighbor1");
        Player player1 =  new Player("Inder");
        player1.addCountryToCountriesOwned(neighbor1);
        d_countries.add(neighbor1);
        Country neighbor2 = new Country("Neighbor2");
        d_player.addCountryToCountriesOwned(neighbor2);
        d_countries.add(neighbor2);
        l_country.addNeighbor(neighbor1);
        l_country.addNeighbor(neighbor2);
        Map<String, Country> l_neighbors = l_country.getNeighbors();
        assertTrue(l_neighbors.containsKey("Neighbor1") || l_neighbors.containsKey("Neighbor2"));
    }

    @Test
    public void testToAttackFrom() {
        Country l_country1 = new Country("Country1");
        d_player.addCountryToCountriesOwned(l_country1);
        Country l_country2 = new Country("Country2");
        d_player.addCountryToCountriesOwned(l_country2);
        d_countries.add(l_country1);
        d_countries.add(l_country2);
        assertTrue(d_countries.contains(d_randomStrategy.toAttackFrom()));
    }

    @Test
    public void testToMoveFrom() {
        Country l_country1 = new Country("Country1");
        d_player.addCountryToCountriesOwned(l_country1);
        Country l_country2 = new Country("Country2");
        d_player.addCountryToCountriesOwned(l_country2);
        d_countries.add(l_country1);
        d_countries.add(l_country2);
        assertTrue(d_countries.contains(d_randomStrategy.toMoveFrom()));
    }

    @Test
    public void testToDefend() {
        Country l_country = new Country("Country1");
        d_player.addCountryToCountriesOwned(l_country);
        d_countries.add(l_country);

        Country l_neighbor1 = new Country("Neighbor1");
        Player l_player1 = new Player("Player1");
        l_player1.addCountryToCountriesOwned(l_neighbor1);
        Country l_neighbor2 = new Country("Neighbor2");
        Player l_player2 = new Player("Player2");
        l_player2.addCountryToCountriesOwned(l_neighbor2);
        l_country.addNeighbor(l_neighbor1);
        l_country.addNeighbor(l_neighbor2);
        Map<String, Country> l_neighbors = l_country.getNeighbors();
        assertTrue(l_neighbors.containsKey("Neighbor1") || l_neighbors.containsKey("Neighbor2"));
    }
}
