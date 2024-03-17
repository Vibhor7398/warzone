/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 2.0
 */
package Models;
import Constants.PhasesEnum;
/**
 * Phase class to store the current phase of the game
 */
public class Phase {
    PhasesEnum d_phase;
    /**
     * Constructor to initialize the phase
     * @param p_phase phase of the game
     */
    public Phase(PhasesEnum p_phase) {
        this.d_phase = p_phase;
    }
    /**
     * Get the phase of the game
     * @return phase of the game
     */
    public PhasesEnum getPhase() {
        return d_phase;
    }
    /**
     * Set the phase of the game
     * @param phase phase of the game
     */
    public void setPhase(PhasesEnum phase) {
        this.d_phase = phase;
    }
}
