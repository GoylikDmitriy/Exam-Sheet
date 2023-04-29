package back.sender.exception;

/**
 * Represents a class of exception,
 * that is thrown when you try to
 * send data
 * @author Goylik D.V.
 */
public class SenderException extends Exception {
    public SenderException() {
    }

    public SenderException(String message) {
        super(message);
    }

    public SenderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SenderException(Throwable cause) {
        super(cause);
    }
}
