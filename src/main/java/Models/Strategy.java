package Models;

/**
 * The Strategy enum defines the types of strategies that can be used by players in the game.
 * Each strategy type is associated with a specific behavior or approach a player takes during the game,
 * influencing decisions such as attack, defense, and territory management. The strategies are differentiated
 * by their inherent tactics ranging from aggressive to cooperative or unpredictable behaviors.
 */
public enum Strategy {
    /**
     * Represents an aggressive strategy where the player prioritizes attacking and expanding territory.
     */
    Aggressive(0),
    /**
     * Represents a benevolent strategy focused on defense and fortifying positions rather than expansion.
     */
    Benevolent(1),
    /**
     * Represents a cheater strategy, which may involve bending rules or exploiting game mechanics to gain an advantage.
     */
    Cheater(2),
    /**
     * Represents a random strategy where decisions are made unpredictably, without a clear long-term plan.
     */
    Random(3),
    /**
     * Represents a human strategy, indicating the player is not an AI and may use complex, flexible strategies.
     */
    Human(4);

    /**
     * The value associated with each strategy, intended for use in scenarios where numerical representation
     * is beneficial, such as storage, transmission, or simple comparisons.
     */
    public int value;

    /**
     * Constructor for the Strategy enum.
     * @param number The numerical representation of the strategy.
     */
    Strategy(int number) {
        this.value = number;
    }
}
