package Exception;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(String p_message){
        super(p_message);
    }
    public String getValidCommand(){
        return super.getMessage();
    }

}
