package Services;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(){
        super("Invalid Command");
    }
    public InvalidCommandException(String p_message){
        super(p_message);
    }
    public String getValidCommand(){
        return "Invalid number of arguments! \n Maybe the command is: "+ super.getMessage();
    }
}
