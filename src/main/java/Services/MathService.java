/**
 *
 *
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Girmire, Inderjeet Singh, Md. Zaid
 * @version 1.0
 */
package Services;

/**
 * This class provides mathematical utility functions.
 */
public class MathService {

    /**
     * Checks if the given input string represents an integer.
     * @param p_input The input string to be checked.
     * @return true if the input string represents an integer, false otherwise.
     */
    public boolean isInteger(String p_input) {
        try {
            Integer.parseInt(p_input);
            return true;
        }
        catch(Exception l_e) {
            return false;
        }
    }
}
