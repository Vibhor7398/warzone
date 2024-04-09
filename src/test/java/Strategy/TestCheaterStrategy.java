package Strategy;

import static org.junit.Assert.*;
import Controller.GameEngineController;
import Models.Strategy;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import Models.Country;
import Models.Player;
import Orders.Order;

public class TestCheaterStrategy {

    private CheaterStrategy d_cheaterStrategy;
    private Player d_player;
    private List<Country> d_countries;
    GameEngineController d_gc = new GameEngineController();

    @Before
    public void setUp() {
        d_gc.executeAddGamePlayer("TestPlayer", Strategy.Human);
        d_player = GameEngineController.d_Players.getFirst();

        d_countries = new ArrayList<>();
        // Adding some countries for testing
        d_countries.add(new Country("Country1"));
        d_countries.add(new Country("Country2"));
        d_countries.add(new Country("Country3"));
        // Setting ownership of countries
        for (Country l_country : d_countries) {
            d_player.addCountryToCountriesOwned(l_country);
            l_country.setArmies(1);
        }
        d_cheaterStrategy = new CheaterStrategy(d_player, d_countries);
    }

    @Test
    public void testCreateOrder() {
        // Creating some neighbors for countries
        d_gc.executeAddGamePlayer("Player1",Strategy.Human);
        Player l_player1 = GameEngineController.d_Players.get(1);
        Country l_neighborsCountry1 = new Country("Neighbor1");
        l_player1.addCountryToCountriesOwned(l_neighborsCountry1);
        d_countries.getFirst().addNeighbor(l_neighborsCountry1);

        d_gc.executeAddGamePlayer("Player2",Strategy.Human);
        Player l_player2 = GameEngineController.d_Players.get(2);
        Country l_neighborsCountry2 = new Country("Neighbor2");
        l_player2.addCountryToCountriesOwned(l_neighborsCountry2);
        d_countries.get(1).addNeighbor(l_neighborsCountry2);

        l_neighborsCountry2.setArmies(1);
        l_neighborsCountry1.setArmies(1);

        List<Country> l_neighborCountries = new ArrayList<>();
        l_neighborCountries.add(l_neighborsCountry1);
        l_neighborCountries.add(l_neighborsCountry2);

        // Before executing createOrder, the player should own all countries
        for (Country l_country : d_countries) {
            assertEquals(d_player, l_country.getOwner());
        }

        d_gc.executeAddGamePlayer("Player3",Strategy.Human);
        Player l_player3 = GameEngineController.d_Players.get(3);
        Country l_neighborsCountry3 = new Country("Neighbor3");
        l_player3.addCountryToCountriesOwned(l_neighborsCountry3);
        l_neighborsCountry2.addNeighbor(l_neighborsCountry3);
        l_neighborsCountry3.setArmies(1);

        Order l_order = d_cheaterStrategy.createOrder();
        assertNull(l_order); // Since the method returns null

        // After executing createOrder, the player should own all countries
        for (Country l_country : d_countries) {
            assertEquals(d_player, l_country.getOwner());
        }


        // All neighbors' owners should be the player after executing createOrder
        for (Country l_country : l_neighborCountries) {
            assertEquals(d_player, l_country.getOwner());
        }

        d_countries.add(l_neighborsCountry1);
        d_countries.add(l_neighborsCountry2);


        for (Country l_country : d_countries) {
            for (Country l_neighbor : l_country.getNeighbors().values()) {
                if(l_neighbor.getOwner()!=d_player){
                    assertEquals(2, l_neighbor.getArmies());
                } else {
                    assertEquals(1, l_neighbor.getArmies());
                }
            }
        }
    }
}
