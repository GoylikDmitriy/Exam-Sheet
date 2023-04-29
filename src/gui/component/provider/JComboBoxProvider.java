package gui.component.provider;

import javax.swing.*;
import java.util.List;

/**
 * Provides with methods to work with JComboBox.
 * @author Goylik D.V.
 * @see JComboBox
 */
public final class JComboBoxProvider {
    public static final int TEACHER = 0;
    public static final int SUBJECT = 1;
    public static final int GROUP = 2;

    /**
     * JComboBox for Teacher list.
     */
    private static final JComboBox<String> jcbTeacher = new JComboBox<>();
    /**
     * JComboBox for Subject list.
     */
    private static final JComboBox<String> jcbSubject = new JComboBox<>();
    /**
     * JComboBox for Group list.
     */
    private static final JComboBox<String> jcbGroup = new JComboBox<>();

    private JComboBoxProvider() {}

    /**
     * Gives instance of JComboBox of group.
     * @return instance of JComboBox of group.
     */
    public static JComboBox<String> getJCBGroup() {
        return jcbGroup;
    }

    /**
     * Gives instance of JComboBox of teacher.
     * @return instance of JComboBox of teacher.
     */
    public static JComboBox<String> getJCBTeacher() {
        return jcbTeacher;
    }

    /**
     * Gives instance of JComboBox of subject.
     * @return instance of JComboBox of subject.
     */
    public static JComboBox<String> getJCBSubject() {
        return jcbSubject;
    }

    /**
     * Gives a selected item from specified JComboBox.
     * @param from JComboBox from which item is needed.
     * @return selected item.
     */
    public static String getSelectedItem(int from) {
        String value = "";
        JComboBox<String> jComboBox = getJComboBox(from);
        if (jComboBox != null) {
            value = (String) jComboBox.getSelectedItem();
        }

        return value;
    }

    /**
     * Adds item to specified JComboBox
     * @param item item to add.
     * @param where where item is needed to add.
     */
    public static void addItem(String item, int where) {
        JComboBox<String> jComboBox = getJComboBox(where);
        if (jComboBox != null) {
            if (!contains(item, jComboBox)) {
                jComboBox.addItem(item);
            }
        }
    }

    /**
     * Adds a list of items to specified JComboBox.
     * @param items items to add.
     * @param where where items are needed to add.
     */
    public static void addItems(List<String> items, int where) {
        JComboBox<String> jComboBox = getJComboBox(where);
        if (jComboBox != null) {
            for (String item : items) {
                if (!contains(item, jComboBox)) {
                    jComboBox.addItem(item);
                }
            }
        }
    }

    /**
     * Adds teachers from table, e.g. from Excel Table.
     * @param items items to add.
     */
    public static void addTeacherFromTableItems(List<List<String>> items) {
        addFromTable(items, TEACHER);
    }

    /**
     * Adds subjects from table, e.g. from Excel Table.
     * @param items items to add.
     */
    public static void addSubjectFromTableItems(List<List<String>> items) {
        addFromTable(items, SUBJECT);
    }

    /**
     * Adds groups from table, e.g. from Excel Table.
     * @param items items to add.
     */
    public static void addGroupFromTableItems(List<List<String>> items) {
        addFromTable(items, GROUP);
    }

    /**
     * Adds items from table, e.g. from Excel Table,
     * to specified JComboBox.
     * @param items items to add.
     */
    private static void addFromTable(List<List<String>> items, int where) {
        JComboBox<String> jComboBox = getJComboBox(where);
        if (jComboBox != null) {
            for (List<String> list : items) {
                for (String item : list) {
                    if (!contains(item, jComboBox)) {
                        jComboBox.addItem(item);
                    }
                }
            }
        }
    }

    /**
     * Checks if JComboBox contains this item.
     * @param item item to check.
     * @param jComboBox JComboBox to check.
     * @return true if it contains, else false.
     */
    private static boolean contains(String item, JComboBox<String> jComboBox) {
        boolean contains = false;
        for (int i = 0; i < jComboBox.getItemCount(); i++) {
            if (jComboBox.getItemAt(i).equals(item)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    private static JComboBox<String> getJComboBox(int where) {
        return switch (where) {
            case TEACHER -> jcbTeacher;
            case SUBJECT -> jcbSubject;
            case GROUP -> jcbGroup;
            default -> null;
        };
    }
}