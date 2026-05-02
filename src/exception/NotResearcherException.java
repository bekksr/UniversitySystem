package exception;

public class NotResearcherException extends Exception {
    private String message;

    public NotResearcherException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
