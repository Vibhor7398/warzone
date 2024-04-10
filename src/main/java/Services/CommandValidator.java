package Services;

import Constants.AppConstants;
import Controller.GameEngineController;
import Controller.MapsController;
import Models.Command;
import Models.Strategy;
import Models.ValidCommands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import Exception.InvalidCommandException;

/**
 * The CommandValidator class is responsible for validating user input commands
 * against a predefined set of valid commands. It ensures that the commands
 * meet the expected format, including the base command, optional subcommands,
 * and the correct number and types of arguments.
 */
public class CommandValidator {
    // Array of valid commands supported by the application
    ValidCommands[] d_validCommands = {
            // Definitions of valid commands go here
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
                    new String[]{"countryName", "neighborCountryName"},
                    new String[]{"String", "String"}),
            new ValidCommands("editneighbor",
                    "-remove",
                    2,
                    new String[]{"countryName", "neighborCountryName"},
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
            new ValidCommands("gameplayer",
                    "-cpu",
                    2,
                    new String[]{"playerName", "strategy"},
                    new String[]{"String", "String"}),
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
                    new String[]{"countryNameFrom", "countryNameTo", "countryValue"},
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
                    new String[]{"sourceCountryName", "targetCountryName", "countryValue"},
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

    /**
     * Checks if a base command has an associated subcommand.
     *
     * @param p_baseCommand The base command to check.
     * @return true if there is an associated subcommand, false otherwise.
     */
    private boolean hasSubCommand(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return !l_validCommand.getSubCommand().isEmpty();
            }
        }
        return false;
    }

    /**
     * Determines if the specified base command expects arguments.
     *
     * @param p_baseCommand The base command to check.
     * @return true if the command expects arguments, false otherwise.
     */
    private boolean hasArguments(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return l_validCommand.getNumArgs() > 0;
            }
        }
        return false;
    }

    /**
     * Validates if the provided base command is recognized as a valid command.
     *
     * @param p_baseCommand The command to validate.
     * @return true if the command is valid, false otherwise.
     */
    private boolean validateBaseCommand(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves the ValidCommands object corresponding to the provided base command.
     *
     * @param p_baseCommand The base command to retrieve the ValidCommands object for.
     * @return The corresponding ValidCommands object, or null if not found.
     */
    private ValidCommands getValidCommandObject(String p_baseCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand)) {
                return l_validCommand;
            }
        }
        return null;
    }

    /**
     * Retrieves the ValidCommands object for a specific combination of base command and subcommand.
     *
     * @param p_baseCommand The base command.
     * @param p_subCommand The subcommand.
     * @return The corresponding ValidCommands object, or null if not found.
     */
    private ValidCommands getValidCommandObject(String p_baseCommand, String p_subCommand) {
        for (ValidCommands l_validCommand : d_validCommands) {
            if (l_validCommand.getBaseCommand().equals(p_baseCommand) && l_validCommand.getSubCommand().equals(p_subCommand)) {
                return l_validCommand;
            }
        }
        return null;
    }

    /**
     * Checks if the provided argument is a valid subcommand.
     *
     * @param p_subCommand The subcommand to validate.
     * @return true if the subcommand is valid, false otherwise.
     */
    private boolean isValidSubCommand(String p_subCommand) {
        return p_subCommand.equals("-add") || p_subCommand.equals("-remove") || p_subCommand.equals("-cpu");
    }

    /**
     * Determines if the given argument represents an integer.
     *
     * @param p_arg The argument to check.
     * @return true if the argument is an integer, false otherwise.
     */
    private boolean isInt(String p_arg) {
        try {
            Integer.parseInt(p_arg);
        } catch (NumberFormatException l_e) {
            return false;
        }
        return true;
    }

    /**
     * Determines if the given argument represents a string.
     * This method is a bit misleading as it essentially checks if the argument is not an integer.
     *
     * @param p_arg The argument to check.
     * @return true if the argument is a string (i.e., not an integer), false otherwise.
     */
    private boolean isString(String p_arg) {
        try {
            Integer.parseInt(p_arg);
        } catch (NumberFormatException l_e) {
            return true;
        }
        return false;
    }

    /**
     * Validates if the provided argument matches the expected data type.
     *
     * @param p_type The expected data type.
     * @param p_arg The argument to validate.
     * @return true if the argument matches the expected type, false otherwise.
     */
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

    /**
     * Constructs a helpful message suggesting the correct usage of a command.
     *
     * @param p_baseCommand The base command for which to construct the usage message.
     * @return A string containing the usage message.
     */
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

    /**
     * Validates the user input command and constructs an array of Command objects
     * if the input is valid. Throws an InvalidCommandException otherwise.
     *
     * @param p_command The user input command to validate.
     * @return An array of Command objects representing the validated command.
     * @throws InvalidCommandException If the command is invalid.
     */
    public Command[] validateCommand(String p_command) throws InvalidCommandException {
            String[] l_cmd = p_command.trim().split(" ");
            String l_baseCommand = l_cmd[0];
            if(l_baseCommand.equals("tournament")){
                Command res = validateTournamentCmd(l_cmd);
                if(res == null){
                    throw new InvalidCommandException("Invalid Command!");
                }
                else{
                    return new Command[]{res};
                }
            }
            if (!validateBaseCommand(l_baseCommand)) {
                throw new InvalidCommandException("Invalid Command!");
            }
            int commandPointer = 1;

            if (!hasSubCommand(l_baseCommand) && !hasArguments(l_baseCommand)) {
                if(l_cmd.length!=1)
                {
                    throw new InvalidCommandException("Invalid Command!");
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
                if (!l_cmd[1].equals("-add") && !l_cmd[1].equals("-remove") && !l_cmd[1].equals("-cpu")) {
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
            return l_commands;
    }

    private Command validateTournamentCmd(String[] p_cmd){
        // tournament -M listofmapfiles -P listofplayerstrategies -G numberofgames -D maxnumberofturns
        if(!(p_cmd[1].equals("-M") && p_cmd[3].equals("-P") && p_cmd[5].equals("-G") && p_cmd[7].equals("-D"))){
            return null;
        }
        try{
            boolean isValid = validateMaps(p_cmd[2]) && validatePlayerStrategies(p_cmd[4]) && validateGames(p_cmd[6]) && validateTurns(p_cmd[8]);
            if(!isValid){
                return null;
            }
            String[] l_args = new String[4];
            l_args[0] = p_cmd[2]; //maps
            l_args[1] = p_cmd[4]; //players
            l_args[2] = p_cmd[6]; //games
            l_args[3] = p_cmd[8]; //turns
            return new Command(p_cmd[0],"",l_args);
        }
        catch(Exception ex){
            return null;
        }
    }

    private boolean validateMaps(String p_listOfMaps) throws IOException {
        GameEngineController l_gc = new GameEngineController();
        String[] l_listOfMaps = p_listOfMaps.split(",");
        if(l_listOfMaps.length > 5 || l_listOfMaps.length < 1){
            System.out.println("Invalid number of maps");
            return false;
        }
        for(String l_filename: l_listOfMaps){
            if(!l_gc.executeLoadMap(l_filename.trim())){
                return false;
            }
        }
        return true;
    }

    private boolean validatePlayerStrategies(String p_listOfPlayerStrategies){
        String[] l_listOfPlayerStrategies = p_listOfPlayerStrategies.split(",");
        if(l_listOfPlayerStrategies.length > 4 || l_listOfPlayerStrategies.length < 2){
            System.out.println("Invalid number of player strategies");
            return false;
        }
        for(String l_strategy: l_listOfPlayerStrategies){
            boolean isValid = false;
            for(int i = 0; i <Strategy.values().length; i++){
                if(l_strategy.equals(Strategy.values()[i].name())){
                    isValid = true;
                    break;
                }
            }
            if (!isValid){
                System.out.println("Invalid Strategy: " + l_strategy);
                return false;
            }
        }
        return true;
    }

    private boolean validateGames(String p_games){
        try {
            int x = Integer.parseInt(p_games);
            if(x < 1 || x > 5){
                throw new Exception();
            }
            return true;
        }
        catch (Exception ex){
            System.out.println("Invalid number of games!");
            return false;
        }
    }

    private boolean validateTurns(String p_turns){
        try {
            int x = Integer.parseInt(p_turns);
            if(x < 10 || x > 50){
                throw new Exception();
            }
            return true;
        }
        catch (Exception ex){
            System.out.println("Invalid number of turns!");
            return false;
        }
    }
}