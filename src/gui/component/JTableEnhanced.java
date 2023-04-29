package gui.component;

import gui.component.model.EnhancedTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

/**
 * Represents a JTable for examination sheet.
 * @author Goylik D.V.
 * @see JTable
 */
public class JTableEnhanced extends JTable {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 350;
    private static final int ROW_HEIGHT = 20;

    /**
     * Constructor of JTableEnhanced.
     */
    public JTableEnhanced() {
        super(new EnhancedTableModel());
        this.setCellCenter();
        this.setColumnWidth();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setRowHeight(ROW_HEIGHT);
        this.setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
        this.getTableHeader().setFont(new Font(Font.DIALOG, Font.BOLD, 12));
        this.getTableHeader().setReorderingAllowed(false);
        this.getTableHeader().setResizingAllowed(false);
        this.setComboBoxToColumn();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDTH, getRowCount() * ROW_HEIGHT);
    }

    /**
     * Sets JComboBox to column in
     * this table.
     */
    private void setComboBoxToColumn() {
        JComboBox<String> comboBox = new JComboBox<>();
        for (int i = 1; i < 11; i++) {
            comboBox.addItem(i + "");
        }

        comboBox.addItem("не явился");
        comboBox.addItem("не допущен");
        getColumn("Оценка").setCellEditor(new DefaultCellEditor(comboBox));
    }

    /**
     * Aligns data to the center.
     */
    private void setCellCenter() {
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        this.getColumnModel().getColumn(0).setCellRenderer(renderer);
        this.getColumnModel().getColumn(3).setCellRenderer(renderer);
        this.getColumnModel().getColumn(4).setCellRenderer(renderer);
    }

    /**
     * Sets columns width.
     */
    private void setColumnWidth() {
        this.getColumnModel().getColumn(0).setPreferredWidth(30);
        this.getColumnModel().getColumn(1).setPreferredWidth(360);
        this.getColumnModel().getColumn(2).setPreferredWidth(140);
        this.getColumnModel().getColumn(3).setPreferredWidth(120);
        this.getColumnModel().getColumn(4).setPreferredWidth(170);
    }
}
