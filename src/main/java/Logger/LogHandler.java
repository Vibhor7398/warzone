package Logger;

import Constants.AppConstants;

import java.io.FileWriter;
import java.io.IOException;

public class LogHandler implements Observer{

    private FileWriter d_logHandler;

    public FileWriter getInstance() throws IOException {
        if(d_logHandler == null){
            d_logHandler = new FileWriter(AppConstants.LogFilePath, false);
            return d_logHandler;
        }
        else{
            return d_logHandler;
        }
    }

    public LogHandler(LogEntryBuffer p_logCollector){
        p_logCollector.addObserver(this);
    }

    @Override
    public void update(Observable p_logState) {
        LogEntryBuffer l_logCollector = (LogEntryBuffer) p_logState;
        try {
//            d_logHandler = new FileWriter("src/main/resources/Logs/Log.log", false);
            getInstance().write(l_logCollector.toString());
            getInstance().close();
        } catch (IOException p_ioException){
            p_ioException.getMessage();
        }
    }
}
