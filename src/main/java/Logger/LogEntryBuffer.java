package Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * LogEntryBuffer class represents a buffer for storing log entries.
 * It extends Observable to support observation of log updates.
 */
public class LogEntryBuffer extends Observable {

    /** List to store log entries */
    private static List<String> d_logList = new ArrayList<>();

    /**
     * Notifies observers of a new log entry along with the phase it occurred in.
     * @param p_log The log message.
     * @param p_phase The phase during which the log occurred.
     */
    public void notify(String p_log, String p_phase) {
        d_logList.add("Log: " + p_log + " during the phase: "+p_phase+"\n");
        notifyAll(this);
    }

    /**
     * Overrides toString method to provide string representation of log entries.
     * @return String representation of log entries.
     */
    @Override
    public String toString() {
        return d_logList.toString();
    }
}
