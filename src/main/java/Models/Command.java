package Models;

import java.util.Arrays;

public class Command {
    private String d_cmd;
    private String d_subCmd;
    private String[] d_args;

    public Command(String p_cmd, String p_subCmd, String[] p_args) {
        d_cmd = p_cmd;
        d_subCmd = p_subCmd;
        d_args = p_args;
    }
    public String toString() {
        return "Command: " + d_cmd + " SubCommand: " + d_subCmd + " Args: " + Arrays.toString(d_args);
    }
    public String getD_cmd() {
        return d_cmd;
    }

    public void setD_cmd(String d_cmd) {
        this.d_cmd = d_cmd;
    }

    public String[] getArgs() {
        return d_args;
    }

    public void setArgs(String[] args) {
        this.d_args = args;
    }

    public String getD_subCmd() {
        return d_subCmd;
    }

    public void setD_subCmd(String d_subCmd) {
        this.d_subCmd = d_subCmd;
    }
}
