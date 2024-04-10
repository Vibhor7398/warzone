/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 3.0
 */

package Models;

/**
 * Represents a valid command along with its subcommand, number of arguments, argument values, and argument types.
 */
public class ValidCommands {
    private String d_baseCommand;
    private String d_subCommand;
    private int d_numArgs;
    private String[] d_args;
    private String[] d_argsTypes;

     /**
     * Constructs a valid command with the specified parameters.
     *
     * @param p_command    The base command.
     * @param p_subCommand The subcommand.
     * @param p_numArgs    The number of arguments.
     * @param p_args       The argument values.
     * @param p_argsTypes  The argument types.
     */
    public ValidCommands(String p_command, String p_subCommand, int p_numArgs, String[] p_args, String[] p_argsTypes) {
        d_baseCommand = p_command;
        d_subCommand = p_subCommand;
        d_numArgs = p_numArgs;
        d_args = p_args;
        d_argsTypes = p_argsTypes;
    }

    /**
     * Retrieves the base command.
     *
     * @return The base command.
     */
    public String getBaseCommand() {
        return d_baseCommand;
    }

    /**
     * Retrieves the subcommand.
     *
     * @return The subcommand.
     */
    public String getSubCommand() {
        return d_subCommand;
    }

    /**
     * Retrieves the number of arguments.
     *
     * @return The number of arguments.
     */
    public int getNumArgs() {
        return d_numArgs;
    }

    /**
     * Retrieves the argument values.
     *
     * @return The argument values.
     */
    public String[] getArgs() {
        return d_args;
    }

    /**
     * Retrieves the argument types.
     *
     * @return The argument types.
     */
    public String[] getArgsTypes() {
        return d_argsTypes;
    }
}
