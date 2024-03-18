/**
 * @author Vibhor Gulati, Apoorva Sharma, Saphal Ghimire, Inderjeet Singh Chauhan, Mohammad Zaid Shaikh
 * @version 2.0
 */
package Exception;

/**
 * The InvalidCommandException class represents an exception that is thrown
 * when an invalid command is entered by the user. This exception extends
 * Throwable, making it a checked exception that must be declared or handled
 * where it can be thrown. It provides constructors to create exception
 * instances with a message indicating what was invalid about the command,
 * allowing for informative error reporting.
 */
public class InvalidCommandException extends Throwable {

    /**
     * Constructs a new InvalidCommandException with the specified detail message.
     * The detail message is saved for later retrieval by the {@link Throwable#getMessage()} method.
     *
     * @param validCommand the detail message. The detail message is a String that describes
     *                     this particular exception, which in this case is the invalid command
     *                     that triggered the exception.
     */
    public InvalidCommandException(String validCommand) {
        super(validCommand);
    }

}
