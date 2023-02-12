package tplab1.application.exception;

public class ApplicationException extends RuntimeException {

    private String message;

    public ApplicationException(String message) {
        super(message);
    }
}
