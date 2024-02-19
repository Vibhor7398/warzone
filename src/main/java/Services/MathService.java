package Services;

public class MathService {
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
