package Services;

import Models.Command;
import Models.ValidCommands;
import java.util.ArrayList;
import java.util.Arrays;



public class CommandValidator {
    ValidCommands[] d_validCommands = {
            new ValidCommands("loadmap",
                    "",
                    1,
                    new String[]{"mapName.map"},
                    new String[]{"String"}),
            new ValidCommands("showmap",
                    "",
                    0,
                    new String[]{""},
                    new String[]{""}),
            new ValidCommands("savemap",
                    "",
                    1,
                    new String[]{"mapName.map"},
                    new String[]{"String"}),
            new ValidCommands("editmap",
                    "",
                    1,
                    new String[]{"mapName.map"},
                    new String[]{"Map"}),
            new ValidCommands("editcontinent",
                    "-add",
                    2,
                    new String[]{"continentName", "continentValue"},
                    new String[]{"String", "Number"}),
            new ValidCommands("editcontinent",
                    "-remove",
                    1,
                    new String[]{"continentName"},
                    new String[]{"String"}),
            new ValidCommands("editcountry",
                    "-add",
                    2,
                    new String[]{"countryName", "continentName"},
                    new String[]{"String", "String"}),
            new ValidCommands("editcountry",
                    "-remove",
                    1,
                    new String[]{"countryName"},
                    new String[]{"String"}),
            new ValidCommands("editneighbor",
                    "-add",
                    2,
                    new String[]{"countryName", "countryName"},
                    new String[]{"String", "String"}),
            new ValidCommands("editneighbor",
                    "-remove",
                    2,
                    new String[]{"countryName", "countryName"},
                    new String[]{"String", "String"}),
            new ValidCommands("validatemap",
                    "",
                    0,
                    new String[]{""},
                    new String[]{""}),
            new ValidCommands("gameplayer",
                    "-add",
                    1,
                    new String[]{"playerName"},
                    new String[]{"String"}),
            new ValidCommands("gameplayer",
                    "-remove",
                    1,
                    new String[]{"playerName"},
                    new String[]{"String"}),
            new ValidCommands("assigncountries",
                    "",
                    0,
                    new String[]{""},
                    new String[]{""}),
            new ValidCommands("deploy",
                    "",
                    2,
                    new String[]{"countryName", "countryValue"},
                    new String[]{"String", "Number"}),
            new ValidCommands("advance",
                    "",
                    3,
                    new String[]{"countryName", "countryName", "countryValue"},
                    new String[]{"String", "String", "Number"}),
            new ValidCommands("bomb",
                    "",
                    1,
                    new String[]{"countryName"},
                    new String[]{"String"}),
            new ValidCommands("blockade",
                    "",
                    1,
                    new String[]{"countryName"},
                    new String[]{"String"}),
            new ValidCommands("airlift",
                    "",
                    3,
                    new String[]{"countryName", "countryName", "countryValue"},
                    new String[]{"String", "String", "Number"}),
            new ValidCommands("negotiate",
                    "",
                    1,
                    new String[]{"playerName"},
                    new String[]{"String"}),
            new ValidCommands("endturn",
                    "",
                    0,
                    new String[]{""},
                    new String[]{""})
    };


    private boolean hasSubCommand(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return !l_validCommand.getSubCommand().isEmpty();
            }
        }
        return false;
    }

    private boolean hasArguments(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return l_validCommand.getNumArgs() > 0;
            }
        }
        return false;
    }

    private boolean validateBaseCommand(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return true;
            }
        }
        return false;
    }

    private ValidCommands getValidCommandObject(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return l_validCommand;
            }
        }
        return null;
    }

    private ValidCommands getValidCommandObject(String p_baseCommand, String p_subCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand) && l_validCommand.getSubCommand().equals(p_subCommand)) {
                return l_validCommand;
            }
        }
        return null;
    }

    private boolean isValidSubCommand(String p_subCommand) {
        return p_subCommand.equals("-add") || p_subCommand.equals("-remove");
    }

    private boolean isInt(String p_arg) {
        try {
            Integer.parseInt(p_arg);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean isString(String p_arg) {
        try {
            Integer.parseInt(p_arg);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    private boolean validateType(String p_type, String p_arg) {
        if (p_type.equals("Number")) {
            return isInt(p_arg);
        }
        if (p_type.equals("Map")) {
            return p_arg.endsWith(".map");
        }
        if (p_type.equals("String")) {
            return isString(p_arg);
        }
        return false;
    }

    private String getValidCommand(String p_baseCommand) {
        StringBuilder l_args = new StringBuilder();
        ValidCommands l_validCommand = getValidCommandObject(p_baseCommand);
        if (l_validCommand == null) {
            return "";
        }
        l_args.append(l_validCommand.getBaseCommand()).append(" ");
        if (l_validCommand.getSubCommand().equals("-add") || l_validCommand.getSubCommand().equals("-remove")) {
            l_args.append(l_validCommand.getSubCommand());
        }

        for (int i = 0; i < l_validCommand.getArgs().length; i++) {
            l_args.append(l_validCommand.getArgs()[i]).append(" (").append(l_validCommand.getArgsTypes()[i]).append(")");
            if (i != l_validCommand.getArgs().length - 1) {
                l_args.append(" | ");
            }
        }

        return "Oops! You've missed the command. Did you mean to use -> " + l_args;
    }

    public Command[] validateCommand(String p_command) throws InvalidCommandException {
            String[] l_cmd = p_command.trim().split(" ");
            String l_baseCommand = l_cmd[0];
            if (!validateBaseCommand(l_baseCommand)) {
                throw new InvalidCommandException("Invalid Command!!");
            }
            int commandPointer = 1;

            if (!hasSubCommand(l_baseCommand) && !hasArguments(l_baseCommand)) {
                if(l_cmd.length!=1)
                {
                    throw new InvalidCommandException("Invalid Command");
                }
                return new Command[]{new Command(l_baseCommand, "", new String[]{})};
            }
            if (l_cmd.length == 1) {
                throw new InvalidCommandException(getValidCommand(l_baseCommand));
            }
            ValidCommands l_validCommand = getValidCommandObject(l_baseCommand);
            if (l_validCommand == null) {
                throw new InvalidCommandException(getValidCommand(l_baseCommand));
            }
            if (isValidSubCommand(l_cmd[1])) {
                l_validCommand = getValidCommandObject(l_baseCommand, l_cmd[1]);
                if (l_validCommand == null) {
                    throw new InvalidCommandException(getValidCommand(l_baseCommand));
                }
                if (!l_cmd[1].equals("-add") && !l_cmd[1].equals("-remove")) {
                    throw new InvalidCommandException(getValidCommand(l_baseCommand));
                }
                commandPointer++;
            }
            if (l_cmd.length == commandPointer) {
                throw new InvalidCommandException(getValidCommand(l_baseCommand));
            }
            ArrayList<String> l_argsArray = new ArrayList<>(Arrays.asList(l_cmd).subList(commandPointer, l_cmd.length));
            if (l_argsArray.size() % l_validCommand.getNumArgs() != 0) {
                throw new InvalidCommandException(getValidCommand(l_baseCommand));
            }
            for (int i = 0; i < l_argsArray.size(); i++) {
                if (l_argsArray.get(i).isEmpty()) {
                    throw new InvalidCommandException(getValidCommand(l_baseCommand));
                }
                if (l_validCommand.getArgsTypes()[i % l_validCommand.getNumArgs()].isEmpty()) {
                    throw new InvalidCommandException(getValidCommand(l_baseCommand));
                }
                if (!validateType(l_validCommand.getArgsTypes()[i % l_validCommand.getNumArgs()], l_argsArray.get(i))) {
                    throw new InvalidCommandException(getValidCommand(l_baseCommand));
                }
            }
            int size=l_argsArray.size() / l_validCommand.getNumArgs();
            Command[] l_commands = new Command[size];
            for (int i = 0; i < size; i++) {
                if(hasSubCommand(l_baseCommand)){
                    l_commands[i] = new Command(l_baseCommand, l_cmd[1], l_argsArray.subList(i * l_validCommand.getNumArgs(), (i + 1) * l_validCommand.getNumArgs()).toArray(new String[0]));
                }
                else{
                    l_commands[i] = new Command(l_baseCommand, "", l_argsArray.subList(i * l_validCommand.getNumArgs(), (i + 1) * l_validCommand.getNumArgs()).toArray(new String[0]));
                }
            }
            for(Command c:l_commands){
                System.out.println(c.toString());
            }
            return l_commands;
    }


}
