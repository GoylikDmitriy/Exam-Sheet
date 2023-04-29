package gui.component.provider;

import gui.component.JTableEnhanced;
import gui.component.model.EnhancedTableModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides with methods to work with JTableEnhanced.
 * @author Goylik D.V.
 * @see JTableEnhanced
 */
public final class JTableEnhancedProvider {
    /**
     * Instance of JTableEnhanced to work with.
     */
    private static final JTableEnhanced table = new JTableEnhanced();
    /**
     * Corresponding TableModel for JTableEnhanced.
     */
    private static final EnhancedTableModel tableModel = (EnhancedTableModel) table.getModel();

    /**
     * Private constructor of JTableEnhancedProvider.
     */
    private JTableEnhancedProvider() {}

    /**
     * Gives the instance of JTableEnhanced.
     * @return the instance of JTableEnhanced.
     */
    public static JTableEnhanced getTable() {
        return table;
    }

    /**
     * Set value to specified column.
     * @param value value to set.
     * @param column index of column to set.
     */
    public static void setValueAtColumn(Object value, int column) {
        tableModel.setValueAtColumn(value, column);
    }

    /**
     * Gives all values from specified column.
     * @param column column where values are taken.
     * @return List of values.
     */
    public static List<Object> getValuesAtColumn(int column) {
        return tableModel.getValuesAtColumn(column);
    }

    /**
     * Gives a value from specified row and column.
     * @param row row index.
     * @param column column index.
     * @return value from specified row and column.
     */
    public static Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }

    /**
     * Sets value to specified row and column.
     * @param value value to set.
     * @param row row index.
     * @param column column index.
     */
    public static void setValueAt(Object value, int row, int column) {
        tableModel.setValueAt(value, row, column);
    }

    /**
     * Adds row to table.
     * @param data data to add.
     */
    public static void addRow(Object[] data) {
        tableModel.addRow(data);
    }

    /**
     * Removes last row from table.
     */
    public static void removeRow() {
        tableModel.removeRow(table.getSelectedRow());
    }

    /**
     * Gives count of rows.
     * @return count of rows.
     */
    public static int getRowCount() {
        return tableModel.getRowCount();
    }

    /**
     * Gives count of columns.
     * @return count of columns.
     */
    public static int getColumnCount() {
        return table.getColumnCount();
    }

    /**
     * Adds rows to table.
     * @param data list of rows.
     */
    public static void addRows(List<List<String>> data) {
        tableModel.addRows(data);
    }

    /**
     * Calculates the result and return it
     * in format [grade, count].
     * @return result of table.
     */
    public static Map<String, Integer> getStats() {
        int col = 3;
        Map<String, Integer> stats = new HashMap<>();
        List<Object> values = getValuesAtColumn(col);
        for (Object value : values) {
            if (value != null) {
                switch ((String) value) {
                    case "1" -> increment(stats, "one");
                    case "2" -> increment(stats, "two");
                    case "3" -> increment(stats, "three");
                    case "4" -> increment(stats, "four");
                    case "5" -> increment(stats, "five");
                    case "6" -> increment(stats, "six");
                    case "7" -> increment(stats, "seven");
                    case "8" -> increment(stats, "eight");
                    case "9" -> increment(stats, "nine");
                    case "10" -> increment(stats, "ten");
                    default -> increment(stats, "notallowed");
                }
            }
        }

        return stats;
    }

    private static void increment(Map<String, Integer> stats, String key) {
        if (stats.containsKey(key)) {
            int value = stats.get(key);
            stats.replace(key, value, ++value);
        }
        else {
            stats.put(key, 1);
        }
    }
}