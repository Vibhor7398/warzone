package Exception;
/**
 * Exception thrown when an invalid command is encountered.
 */
public class InvalidCommandException extends Exception{
    /**
     * Constructs an InvalidCommandException with the specified detail message.
     *
     * @param p_message The detail message.
     */
    public InvalidCommandException(String p_message){
        super(p_message);
    }
    
    /**
     * Retrieves the valid command message associated with the exception.
     *
     * @return The valid command message.
     */
    public String getValidCommand(){
        return super.getMessage();
    }

}
