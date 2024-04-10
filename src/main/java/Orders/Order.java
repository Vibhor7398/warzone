/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
package Orders;

import java.io.Serializable;

/**
 * Represents a generic game order in a strategy game framework. This interface defines the basic structure
 * for orders that can be issued by players, such as moving armies, attacking, or deploying special actions.
 * Implementing classes are expected to define specific behaviors for validation, execution, and reporting of these orders.
 */
public interface Order extends Serializable {
    /**
     * Validates whether the order is legal and can be executed within the current game state.
     * This method should check all necessary conditions such as ownership of territories, availability of resources,
     * or any other game-specific rules that might prevent the order from being carried out.
     *
     * @return true if the order is valid and can be executed, false otherwise.
     */
    public boolean isValid();


    /**
     * Executes the order, applying its effects to the game state. This method should only be called after {@link #isValid()}
     * has returned true. The implementation should handle all necessary game logic to carry out the order, such as moving
     * armies, changing ownership of territories, or applying special effects.
     */
    public void execute();


    /**
     * Prints a summary or the results of the order execution. This method is intended to provide feedback to the players
     * about the outcome of the order, such as success or failure messages, descriptions of the effects applied to the game
     * state, or any other relevant information. It's particularly useful for textual or console-based interfaces to inform
     * players of the actions taken.
     */
    public void print();
}
