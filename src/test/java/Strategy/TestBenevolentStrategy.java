package Strategy;

import Controller.GameEngineController;
import Models.Country;
import Models.Player;
import Orders.Advance;
import Orders.Deploy;
import Orders.Airlift;
import Orders.Order;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestBenevolentStrategy {
    private BenevolentStrategy d_benevolentStrategy;
    private Player d_testPlayer;
    private GameEngineController d_gc;

    @Before
    public void setUp() {
        d_gc = new GameEngineController();
        d_gc.executeAddGamePlayer("TestPlayer");
        d_testPlayer = GameEngineController.d_Players.getFirst();

        List<Country> l_countriesOwned = new ArrayList<>();
        Country l_country1 = new Country("Country1");
        l_countriesOwned.add(l_country1);
        Country l_country2 = new Country("Country2");
        l_countriesOwned.add(l_country2);
        d_testPlayer.addCountryToCountriesOwned(l_country1);
        d_testPlayer.addCountryToCountriesOwned(l_country2);
        l_country1.addNeighbor(l_country2);

        d_benevolentStrategy = new BenevolentStrategy(d_testPlayer, l_countriesOwned);
    }
}
