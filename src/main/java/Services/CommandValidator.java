package Services;
import java.util.Objects;
import java.util.Scanner;

public class CommandValidator {

    public int parseInt(String p_type, String p_value)
    {
        try {
            if (p_type.equals("int")) {
                return Integer.parseInt(p_value);
            }
        } catch (NumberFormatException e) {
            System.out.println("Not a valid integer");
        }
        return -1;
    }
    private ValidCommands getValidCommand(String baseCommand) throws InvalidCommandException {
        try {
            return ValidCommands.valueOf(baseCommand.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("Invalid comment");
        }
    }

    private boolean validateSubCommand(String[] p_command){
        if(p_command.length==1){
            return false;
        }
       return true;
    }
    public void validateCommand(String p_command){
        try {
            String[] l_cmd = p_command.trim().split("\\ ");
            String l_baseCommand = l_cmd[0];
            ValidCommands l_command = getValidCommand(l_baseCommand);
            if(!l_command.hasSubCommand() && !l_command.hasArguments()){
                if(!validateSubCommand(l_cmd)){
                    throw new InvalidCommandException(l_command.getValidCommand());
                }

                nextUserInput();
            }
            if(l_cmd.length==1){
                throw new InvalidCommandException(l_command.getValidCommand());
            }
            String l_subCommand="";
            int commandPointer=1;
            if(l_command.hasSubCommand()){
                 l_subCommand = l_cmd[1];
                 if(!l_command.isValidSubCommand(l_subCommand)){
                     throw new InvalidCommandException(l_command.getValidCommand());
                 }
                 commandPointer++;
            }
            String l_arguments="";
            for(int i=commandPointer;i<l_cmd.length;i++){
                if(l_cmd[i].isEmpty()){
                    throw new InvalidCommandException(l_command.getValidCommand());
                }
                l_arguments+=l_cmd[i]+",";
            }
            String[] l_argsArray=l_arguments.trim().split(",");
            String[] l_argsArrayType=l_command.getArgsType();
            int l_numArgs=l_command.getNumArgs();
                for (int i = 0; i < l_argsArray.length; i++) {
                    l_argsArray[i] = l_argsArray[i].trim();
                    if (l_argsArrayType[i].isEmpty()) {
                        System.out.println("Invalid argument type");
                        System.out.println("Maybe the command is: " + l_command.getValidCommand());
                        nextUserInput();
                    }
                    if (Objects.equals(l_argsArrayType[i], "int") && parseInt(l_argsArrayType[i], l_argsArray[i]) == -1) {
                        System.out.println("Invalid argument type");
                        System.out.println("Maybe the command is: " + l_command.getValidCommand());
                        nextUserInput();
                    }
                    if (l_argsArray.length != l_numArgs && l_argsArray.length % l_numArgs != 0) {
                        throw new InvalidCommandException(l_command.getValidCommand());

                    }
                    System.out.println("Split Now");
                    nextUserInput();

                }


        }catch (InvalidCommandException e){
            System.out.println("Invalid Command!");
            System.out.println(e.getMessage());
            nextUserInput();
        }


    }

    public void nextUserInput(){
        Scanner l_sc=new Scanner(System.in);
        System.out.println("Enter your command");
        String l_command=l_sc.nextLine();
        validateCommand(l_command);
    }



}
