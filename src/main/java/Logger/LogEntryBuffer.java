package Logger;

import java.util.ArrayList;
import java.util.List;

public class LogEntryBuffer extends Observable {

    private static List<String> d_logList = new ArrayList<>();

    public void notify(String p_log, String p_phase) {
        d_logList.add("Log: " + p_log + " during the phase: "+p_phase+"\n");
        notifyAll(this);
    }

    @Override
    public String toString() {
        return d_logList.toString();
    }
}
