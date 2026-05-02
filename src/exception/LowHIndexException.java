package exception;

public class LowHIndexException extends Exception {
    public String message;

    public LowHIndexException(String message) {
        super(message);
        this.message = message;
    }
}
