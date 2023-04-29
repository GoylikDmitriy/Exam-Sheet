package back.io.output;

import back.io.exception.WriterException;

/**
 * Allows to write data.
 * Needs to implement one method - write.
 * @author Goylik D.V.
 */
public interface Writer {
    /**
     * Writes data.
     * @throws WriterException
     */
    void write() throws WriterException;
}
