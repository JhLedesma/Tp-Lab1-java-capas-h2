package tplab1.persistency.exception;

public class PersistencyException extends RuntimeException {

    private String message;

    public PersistencyException(String message) {
        super(message);
    }
}
