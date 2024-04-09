package Exception;

/**
 * The MapValidationException class is a custom exception used to indicate errors during map validation processes.
 * This exception extends Throwable, allowing it to be thrown and caught in scenarios where the game map does not meet
 * certain criteria or validation checks. It provides constructors for creating exceptions with or without a detailed
 * message and overrides the getMessage() method to provide a default message when none is specified.
 */
public class MapValidationException extends Throwable {

    /**
     * Constructs a new MapValidationException with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     */
    public MapValidationException() {
        super();
    }
    /**
     * Constructs a new MapValidationException with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message The detail message. The detail message is saved for later retrieval by the {@link #getMessage()} method.
     */
    public MapValidationException(String message) {
        super(message);
    }

    /**
     * Returns the detail message string of this exception.
     * If a message was provided during the exception's construction, this message is returned. Otherwise,
     * a default message indicating an invalid command is returned.
     *
     * @return The detail message string of this {@code Throwable} instance (which may be {@code null}).
     */
    @Override
    public String getMessage() {
        if(super.getMessage() != null) {
            return super.getMessage();
        }
        return "Invalid Command";
    }
}
