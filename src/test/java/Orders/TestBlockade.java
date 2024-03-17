//package Orders;
//
//import static org.junit.Assert.*;
//
//import Controller.GameEngineController;
//import org.junit.Before;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//import Models.Country;
//import Models.Player;
//
//public class TestBlockade {
//    private Player d_player;
//    private Country d_country;
//
//    @Before
//    public void setUp() {
//        GameEngineController gameEngineController = new GameEngineController();
//        gameEngineController.executeAddGamePlayer("TestPlayer");
////        Phases
//        d_player = new Player("TestPlayer");
//        d_country = new Country("TestCountry");
//        d_player.addCountryToCountriesOwned(d_country);
//
//        d_player = new Player("Abhi");
//        d_player.addCard("Bloackade");
//    }
//
//    @Test
//    public void testIsValidWithNullPlayer() {
//        Blockade blockade = new Blockade(null, d_country);
//        assertFalse(blockade.isValid());
//    }
//
//    @Test
//    public void testIsValidWithNullCountry() {
//        Blockade blockade = new Blockade(d_player, null);
//        assertFalse(blockade.isValid());
//    }
//
//    @Test
//    public void testIsValidWithNoBlockadeCard() {
//        Blockade blockade = new Blockade(d_player, d_country);
//        assertFalse(blockade.isValid());
//    }
//
//    @Test
//    public void testIsValidWithValidInputs() {
//        List<String> cardList = new ArrayList<>();
//        d_player.addCard("Blockade");
//        Blockade blockade = new Blockade(d_player, d_country);
//        assertTrue(blockade.isValid());
//    }
//
//    @Test
//    public void testExecute() {
//        d_player.addCard("Blockade");
//        Blockade blockade = new Blockade(d_player, d_country);
//        blockade.execute();
//        assertEquals(0, d_player.getCountriesOwned().size());
//        assertEquals(0, d_country.getArmies());
//    }
//
//    @Test
//    public void testExecuteWithInvalidOrder() {
//        Blockade blockade = new Blockade(d_player, d_country);
//        blockade.execute(); // This should not change the state
//        assertEquals(1, d_player.getCountriesOwned().size());
//        assertEquals(1, d_country.getArmies());
//    }
//
//    @Test
//    public void testPrint() {
//        List<String> cardList = new ArrayList<>();
//        d_player.addCard("Blockade");
//        Blockade blockade = new Blockade(d_player, d_country);
//        blockade.execute();
//    }
//}
