package gui.component.model;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a model for JTableEnhanced.
 * @author Goylik D.V.
 * @see gui.component.JTableEnhanced
 * @see javax.swing.table.TableModel
 */
public class EnhancedTableModel extends AbstractTableModel {
    private final ArrayList<Object[]> data = new ArrayList<>();
    private final String[] columnNames = new String[] { "N", "ФИО", "Ном. зач. книжки", "Оценка", "Оценка прописью" };

    /**
     * Adds rows to table.
     * @param rows rows to add.
     */
    public void addRows(List<List<String>> rows) {
        for (var r : rows) {
            if (r.size() == 0) {
                continue;
            }

            if (r.size() > 1) {
                addRow(new Object[]{null, r.get(0), r.get(1), null, null});
            }
            else {
                addRow(new Object[] {null, r.get(0), null, null, null});
            }
        }
    }

    /**
     * Adds row to table.
     * @param row row to add.
     */
    public void addRow(Object[] row) {
        row[0] = this.data.size() + 1;
        this.data.add(row);
        fireTableRowsInserted(this.data.size() - 1, this.data.size() - 1);
    }

    /**
     * Removes row in table.
     * @param row index of row to remove.
     */
    public void removeRow(int row) {
        this.data.remove(row);
        fireTableRowsDeleted(row, row);
        for (int i = row; i < this.data.size(); i++) {
            this.data.get(i)[0] = i + 1;
        }

        fireTableRowsUpdated(row, this.data.size() - 1);
    }

    /**
     * Gives all values from specified column.
     * @param column index of column which values are needed.
     * @return values from specified column.
     */
    public List<Object> getValuesAtColumn(int column) {
        ArrayList<Object> list = new ArrayList<>();
        for (Object[] d : this.data) {
            list.add(d[column]);
        }

        return list;
    }

    /**
     * Sets value to specified column.
     * @param value value to set.
     * @param column column index.
     */
    public void setValueAtColumn(Object value, int column) {
        for (int i = 0; i < this.data.size(); i++) {
            this.data.get(i)[column] = value;
            fireTableCellUpdated(i, column);
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex != 0;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (aValue == null) {
            return;
        }

        this.data.get(rowIndex)[columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
        if (columnIndex == 3) {
            this.data.get(rowIndex)[columnIndex+1] = switch (aValue.toString()) {
                case "1" -> "один";
                case "2" -> "два";
                case "3" -> "три";
                case "4" -> "четыре";
                case "5" -> "пять";
                case "6" -> "шесть";
                case "7" -> "семь";
                case "8" -> "восемь";
                case "9" -> "девять";
                case "10" -> "десять";
                default -> aValue.toString();
            };
        }

        fireTableCellUpdated(rowIndex, columnIndex + 1);
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public int getRowCount() {
        return this.data.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data.get(rowIndex)[columnIndex];
    }
}