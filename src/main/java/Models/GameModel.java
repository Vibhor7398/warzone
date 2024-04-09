package Models;

import Controller.MapsController;
import Logger.LogEntryBuffer;
import Logger.LogHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class GameModel implements Serializable {

    /**
     * Represents the list of players participating in the game.
     * This ArrayList stores Player objects.
     */
    private ArrayList<Player> d_Players;
    /**
     * Represents the MapsController instance used in the game.
     * This variable holds a reference to the MapsController object.
     */
    private MapsController d_Map;
    /**
     * Represents the MapsController instance used in the game.
     * This variable holds a reference to the MapsController object.
     */
    private int d_currentPlayer;
    /**
     * Represents the number of completed turns in the game.
     * This variable stores an integer value indicating the total number of completed turns.
     */
    private int d_completedTurns;

    /**
     * ArrayList containing cards owned by players.
     * Each element in the ArrayList is a Player object.
     */
    private ArrayList<Player> d_cardsOwnedByPlayer;

    public GameModel() {
        d_Players = new ArrayList<>();
        d_Map = new MapsController();
        d_cardsOwnedByPlayer = new ArrayList<>();
        d_currentPlayer = 0;
        d_completedTurns = 0;
    }

    public ArrayList<Player> getD_Players() {
        return d_Players;
    }

    public MapsController getD_Map() {
        return d_Map;
    }

    public int getD_currentPlayer() {
        return d_currentPlayer;
    }

    public int getD_completedTurns() {
        return d_completedTurns;
    }

    public ArrayList<Player> getD_cardsOwnedByPlayer() {
        return d_cardsOwnedByPlayer;
    }
}
