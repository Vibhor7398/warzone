/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */

package Models;

import Controller.MapsController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The GameModel class represents the model component of the game, containing information about players, the game map,
 * current player turn, completed turns, and cards owned by players.
 * <p>
 * This class is responsible for maintaining the state of the game and providing access to its various attributes.
 * </p>
 * <p>
 * This class is Serializable, allowing instances of GameModel to be serialized and deserialized.
 * </p>
 */
public class GameModel implements Serializable {

    /** The list of players participating in the game. */
    private ArrayList<Player> d_Players;

    /** The controller for managing game maps. */
    private MapsController d_Map;

    /** The index of the current player in the list of players. */
    private int d_currentPlayer;

    /** The number of completed turns in the game. */
    private int d_completedTurns;

    /** The list of cards owned by players in the game. */
    private ArrayList<Player> d_cardsOwnedByPlayer;

    /**
     * Constructs a new GameModel object with initial values.
     * Initializes the list of players, the game map, the list of cards owned by players, current player index, and completed turns.
     */
    public GameModel() {
        d_Players = new ArrayList<>();
        d_Map = new MapsController();
        d_cardsOwnedByPlayer = new ArrayList<>();
        d_currentPlayer = 0;
        d_completedTurns = 0;
    }

    /**
     * Retrieves the list of players participating in the game.
     *
     * @return The list of players.
     */
    public ArrayList<Player> getD_Players() {
        return d_Players;
    }

    /**
     * Retrieves the controller managing the game map.
     *
     * @return The MapsController object managing the game map.
     */
    public MapsController getD_Map() {
        return d_Map;
    }

    /**
     * Retrieves the index of the current player.
     *
     * @return The index of the current player.
     */
    public int getD_currentPlayer() {
        return d_currentPlayer;
    }

    /**
     * Retrieves the number of completed turns in the game.
     *
     * @return The number of completed turns.
     */
    public int getD_completedTurns() {
        return d_completedTurns;
    }

    /**
     * Retrieves the list of cards owned by players in the game.
     *
     * @return The list of cards owned by players.
     */
    public ArrayList<Player> getD_cardsOwnedByPlayer() {
        return d_cardsOwnedByPlayer;
    }
}
