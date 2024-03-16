package Models;

public class ValidCommands {
    private String d_baseCommand;
    private String d_subCommand;
    private int d_numArgs;
    private String[] d_args;
    private String[] d_argsTypes;

    public ValidCommands(String p_command, String p_subCommand, int p_numArgs, String[] p_args, String[] p_argsTypes) {
        d_baseCommand = p_command;
        d_subCommand = p_subCommand;
        d_numArgs = p_numArgs;
        d_args = p_args;
        d_argsTypes = p_argsTypes;
    }
    public String getBaseCommand() {
        return d_baseCommand;
    }
    public String getSubCommand() {
        return d_subCommand;
    }
    public int getNumArgs() {
        return d_numArgs;
    }
    public String[] getArgs() {
        return d_args;
    }
    public String[] getArgsTypes() {
        return d_argsTypes;
    }
}
