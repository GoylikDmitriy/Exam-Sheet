package gui.component;

import gui.component.document.JTextFieldLimitedDocument;

import javax.swing.*;

/**
 * Represents JTextField with limited characters.
 * @author Goylik D.V.
 * @see JTextField
 */
public class JTextFieldLimited extends JTextField {
    private int limit;
    private JTextFieldLimitedDocument doc;

    /**
     * Constructor of JTextFieldLimited.
     * @param limit max number of input characters.
     */
    public JTextFieldLimited(int limit) {
        super();
        this.limit = limit;
        this.doc = new JTextFieldLimitedDocument(limit);
        this.setDocument(this.doc);
    }

    public void setLimit(int limit) {
        this.limit = limit;
        this.doc.setLimit(limit);
    }

    public int getLimit() {
        return limit;
    }
}