package gui.component.document;

import gui.component.JTextFieldLimited;

import javax.swing.text.*;

/**
 * Represents a document for JTextFieldLimited.
 * @author Goylik D.V.
 * @see PlainDocument
 * @see JTextFieldLimited
 */
public class JTextFieldLimitedDocument extends PlainDocument {
    private int limit;

    /**
     * Constructor of JTextFieldLimitedDocument.
     * @param limit max number of input characters.
     */
    public JTextFieldLimitedDocument(int limit) {
        super();
        this.limit = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        if (str == null) {
            return;
        }

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offs, str, a);
        }
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
