package tplab1.presentation.exception;

public class PresentationException extends RuntimeException {

    private String message;

    public PresentationException(String message) {
        super(message);
    }
}
