package back.sender;

import back.sender.exception.SenderException;

/**
 * Allows to send data.
 * Needs to implement one method - send.
 * @author Goylik D.V.
 */
public interface Sender {
    /**
     * Sends data.
     * @throws SenderException
     */
    void send() throws SenderException;
}
