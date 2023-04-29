package back.io.exception;

/**
 * Represents an exception class
 * that indicates on exception
 * while reading data.
 * @author Goylik D.V.
 * @see Exception
 */
public class ReaderException extends Exception {
    public ReaderException() {
        super();
    }

    public ReaderException(String message) {
        super(message);
    }

    public ReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReaderException(Throwable cause) {
        super(cause);
    }
}
