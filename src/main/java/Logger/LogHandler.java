/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghirmire, Inderjeet Singh Chauhan, Mohammad Zaid
 * @version 2.0
 */
package Logger;

import Constants.AppConstants;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The LogHandler class implements the Observer interface and represents a component
 * responsible for handling log entries.
 */
public class LogHandler implements Observer {

    private FileWriter d_logHandler; // FileWriter object for handling log files

    /**
     * Constructs a LogHandler object.
     *
     * @param p_logCollector The LogEntryBuffer to observe for log updates.
     */
    public LogHandler(LogEntryBuffer p_logCollector) {
        p_logCollector.addObserver(this);
    }

    /**
     * Updates the log handler with the latest log entry from the observed LogEntryBuffer.
     *
     * @param p_logState The observed LogEntryBuffer object.
     */
    public void update(Observable p_logState) {
        LogEntryBuffer l_logCollector = (LogEntryBuffer) p_logState;
        try {
            d_logHandler = new FileWriter(AppConstants.LogFilePath, false);
            d_logHandler.write(l_logCollector.toString());
            d_logHandler.close();
        } catch (IOException p_ioException) {
            p_ioException.getMessage();
        }
    }
}
