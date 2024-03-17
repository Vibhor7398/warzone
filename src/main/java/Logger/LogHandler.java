package Logger;

import Constants.AppConstants;

import java.io.FileWriter;
import java.io.IOException;

public class LogHandler implements Observer{

    private FileWriter d_logHandler;

    public LogHandler(LogEntryBuffer p_logCollector){
        p_logCollector.addObserver(this);
    }

    public void update(Observable p_logState) {
        LogEntryBuffer l_logCollector = (LogEntryBuffer) p_logState;
        try {
            d_logHandler = new FileWriter("src/main/resources/Logs/Log.log", false);
            d_logHandler.write(l_logCollector.toString());
            d_logHandler.close();
        } catch (IOException p_ioException){
            p_ioException.getMessage();
        }
    }
}
