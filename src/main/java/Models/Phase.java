package Models;
import Constants.Phases;
/**
 * Phase class to store the current phase of the game
 */
public class Phase {
    Phases d_phase;
    /**
     * Constructor to initialize the phase
     * @param p_phase phase of the game
     */
    public Phase(Phases p_phase) {
        this.d_phase = p_phase;
    }
    /**
     * Get the phase of the game
     * @return phase of the game
     */
    public Phases getPhase() {
        return d_phase;
    }
    /**
     * Set the phase of the game
     * @param phase phase of the game
     */
    public void setPhase(Phases phase) {
        this.d_phase = phase;
    }
}
