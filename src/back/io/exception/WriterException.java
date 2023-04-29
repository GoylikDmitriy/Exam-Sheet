package back.io.exception;

/**
 * Represents an exception class
 * that indicates on exception
 * while writing data.
 * @author Goylik D.V.
 * @see Exception
 */
public class WriterException extends Exception {
    public WriterException() {
    }

    public WriterException(String message) {
        super(message);
    }

    public WriterException(String message, Throwable cause) {
        super(message, cause);
    }

    public WriterException(Throwable cause) {
        super(cause);
    }
}
