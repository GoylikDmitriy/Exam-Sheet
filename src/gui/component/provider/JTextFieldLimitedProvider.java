package gui.component.provider;

import gui.component.JTextFieldLimited;

/**
 * Provides with methods to work with JTextFieldLimited.
 * Provides with to JTextFieldLimited:
 * for Exam Sheet Number and Hours of Subject.
 * @author Goylik D.V.
 */
public final class JTextFieldLimitedProvider {
    public static final int EXAM_NUMBER = 0;
    public static final int HOURS = 1;

    /**
     * JTextFieldLimited for Exam Sheet Number.
     */
    private static final JTextFieldLimited jtfExamNumber = new JTextFieldLimited(7);
    /**
     * JTextFieldLimited for Hours of Subject.
     */
    private static final JTextFieldLimited jtfHours = new JTextFieldLimited(7);

    /**
     * Private constructor of JTextFieldLimitedProvider.
     */
    private JTextFieldLimitedProvider() {}

    /**
     * Gives JTextFieldLimited for Exam Sheet Number.
     * @return JTextFieldLimited for Exam Sheet Number.
     */
    public static JTextFieldLimited getJTFExamNumber() {
        return jtfExamNumber;
    }

    /**
     * Gives JTextFieldLimited for Hours of Subject.
     * @return JTextFieldLimited for Hours of Subject.
     */
    public static JTextFieldLimited getJTFHours() {
        return jtfHours;
    }

    /**
     * Gives text of JTextFieldLimited.
     * @param from from which JTextFieldLimited text is needed.
     * @return text of JTextFieldLimited.
     */
    public static String getText(int from) {
        String text = "";
        JTextFieldLimited jtf = getJTF(from);
        if (jtf != null) {
            text = jtf.getText();
        }

        return text;
    }

    /**
     * Gives needed JTextFieldLimited.
     * @param o type of JTextFieldLimited.
     * @return the corresponding JTextFieldLimited.
     */
    private static JTextFieldLimited getJTF(int o) {
        return switch (o) {
            case EXAM_NUMBER -> jtfExamNumber;
            case HOURS -> jtfHours;
            default -> null;
        };
    }
}