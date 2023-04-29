package back.io.input;

import back.io.exception.ReaderException;

import java.util.function.Consumer;

/**
 * Allows to read data.
 * Needs to implement one method - read.
 * @author Goylik D.V.
 */
public interface Reader {
    /**
     * Reads data.
     * @param consume method that handle read data.
     * @param <T> type of read data.
     * @throws ReaderException
     */
    <T> void read(Consumer<T> consume) throws ReaderException;
}