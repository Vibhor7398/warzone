package Services;

public class InvalidCommandException extends Throwable {
    public InvalidCommandException(String validCommand) {
        super(validCommand);
    }
}
