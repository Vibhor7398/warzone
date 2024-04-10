/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */
package Models;

import java.util.Arrays;
/**
 * Represents a command with its base command, subcommand, and arguments.
 */
public class Command {
    private String d_cmd;
    private String d_subCmd;
    private String[] d_args;
    
    /**
     * Constructs a command with the specified base command, subcommand, and arguments.
     *
     * @param p_cmd    The base command.
     * @param p_subCmd The subcommand.
     * @param p_args   The arguments.
     */
    public Command(String p_cmd, String p_subCmd, String[] p_args) {
        d_cmd = p_cmd;
        d_subCmd = p_subCmd;
        d_args = p_args;
    }

    /**
     * Retrieves the base command.
     *
     * @return The base command.
     */
    public String getD_cmd() {
        return d_cmd;
    }

    /**
     * Sets the base command.
     *
     * @param d_cmd The base command to set.
     */
    public void setD_cmd(String d_cmd) {
        this.d_cmd = d_cmd;
    }

    /**
     * Retrieves the arguments.
     *
     * @return The arguments.
     */
    public String[] getArgs() {
        return d_args;
    }

    /**
     * Sets the arguments.
     *
     * @param args The arguments to set.
     */
    public void setArgs(String[] args) {
        this.d_args = args;
    }

    /**
     * Retrieves the subcommand.
     *
     * @return The subcommand.
     */
    public String getD_subCmd() {
        return d_subCmd;
    }


    /**
     * Sets the subcommand.
     *
     * @param d_subCmd The subcommand to set.
     */
    public void setD_subCmd(String d_subCmd) {
        this.d_subCmd = d_subCmd;
    }

    /**
     * Provides a string representation of the command.
     *
     * @return A string representation of the command.
     */
    public String toString() {
        return "Command: " + d_cmd + " SubCommand: " + d_subCmd + " Args: " + Arrays.toString(d_args);
    }
}
