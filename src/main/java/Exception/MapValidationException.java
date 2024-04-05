package Exception;

public class MapValidationException extends Throwable {
    public MapValidationException() {
        super();
    }

    public MapValidationException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        if(super.getMessage() != null) {
            return super.getMessage();
        }
        return "Invalid Command";
    }
}
