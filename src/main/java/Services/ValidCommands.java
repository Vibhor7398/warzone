package Services;

public enum ValidCommands {
    showmap("showmap",
            new String[]{},
            "0",
            new String[]{},
            new String[]{}),
    deploy("deploy",
            new String[]{"-add", "-remove"},
            "1",
            new String[]{"countryName"},
            new String[]{"int"}),
    attack("attack",
            new String[]{},
            "2",
            new String[]{"country1", "country2"},
            new String[]{"String", "String"});

    private final String d_baseCommand;
    private final String[] d_subCommand;
    final String d_numArgs;
    private final String[] d_args;
    private final String[] d_argsTypes;

    private ValidCommands(String p_command, String[] p_subCommand, String p_numArgs, String[] p_args, String[] p_argsTypes) {
        d_baseCommand = p_command;
        d_subCommand = p_subCommand;
        d_numArgs = p_numArgs;
        d_args = p_args;
        d_argsTypes = p_argsTypes;
    }

    public boolean hasSubCommand() {
        return d_subCommand.length > 0;
    }

    public boolean hasArguments() {
        return d_args.length > 0;
    }

    public boolean isValidSubCommand(String p_subCommand) {
        for (String subCommand : d_subCommand) {
            if (subCommand.equals(p_subCommand)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidArgsType(String p_numArgs) {
        for (String argsType : d_argsTypes) {
            if (argsType.equals(p_numArgs)) {
                return true;
            }
        }
        return false;
    }

    public String[] getArgsType() {
        return d_argsTypes;
    }

    public int getNumArgs() {
        return Integer.parseInt(d_numArgs);
    }

    public String getValidCommand() {
        StringBuilder l_args = new StringBuilder();
        if (hasSubCommand()) {
            l_args.append("[ ");
            for (String args : d_subCommand) {
                if (args.equals(d_subCommand[d_subCommand.length - 1])) {
                    l_args.append(args);
                } else {
                    l_args.append(args).append(" | ");
                }
            }
            l_args.append(" ]");
        }
        l_args.append(" (");
        for (String args : d_args) {
            if (args.equals(d_args[d_args.length - 1])) {
                l_args.append(args);
            } else {
                l_args.append(args).append(" | ");
            }
        }
        l_args.append(")");
        l_args.append(" (");
        for (String args : d_argsTypes) {
            if (args.equals(d_argsTypes[d_argsTypes.length - 1])) {
                l_args.append(args);
            } else {
                l_args.append(args).append(" | ");
            }
        }
        l_args.append(")");

        return d_baseCommand + " " + l_args;
    }

}
