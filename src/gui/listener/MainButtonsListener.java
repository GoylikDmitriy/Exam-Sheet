package gui.listener;

import back.io.exception.ReaderException;
import back.io.exception.WriterException;
import back.io.input.FileReader;
import back.io.input.provider.FileReaderProvider;
import back.io.output.Writer;
import back.io.output.impl.DOCXFileWriter;
import gui.component.provider.JTableEnhancedProvider;
import gui.component.provider.JTextFieldLimitedProvider;
import gui.component.provider.JXDatePickerProvider;
import gui.window.SenderWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Listener class for buttons
 * 'Add', 'Remove', 'Open', 'Save' and 'Send'.
 * @author Goylik D.V.
 */
public class MainButtonsListener implements ActionListener {
    private final String ADD = "Add";
    private final String REMOVE = "Remove";
    private final String OPEN = "Open";
    private final String SAVE = "Save";
    private final String SEND = "Send";

    /**
     * Handle events from buttons:
     * 'Add', 'Remove', 'Open', 'Save' and 'Send'.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton jb) {
            switch (jb.getText()) {
                case ADD -> JTableEnhancedProvider.addRow(new Object[] {null,"", "", "", ""});
                case REMOVE -> JTableEnhancedProvider.removeRow();
                case OPEN -> new Thread(() -> {
                    try {
                        JFileChooser fileChooser = new JFileChooser("resources/excel");
                        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                            File file = fileChooser.getSelectedFile();
                            FileReader reader = FileReaderProvider.getFileReader(file.getPath());
                            reader.read(JTableEnhancedProvider::addRows);
                        }
                    } catch (ReaderException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }).start();
                case SAVE -> {
                    try {
                        if (JTextFieldLimitedProvider.getText(JTextFieldLimitedProvider.EXAM_NUMBER).isEmpty()
                                || JTextFieldLimitedProvider.getText(JTextFieldLimitedProvider.HOURS).isEmpty()
                                || JXDatePickerProvider.getDateString().isEmpty()
                        ) {
                            JOptionPane.showMessageDialog(null, "Not all fields are filled.", "Warning", JOptionPane.PLAIN_MESSAGE);
                        } else {
                            JFileChooser fileChooser = new JFileChooser("resources/docx");
                            fileChooser.setFileFilter(new FileFilter() {
                                @Override
                                public boolean accept(File f) {
                                    String[] s = f.getName().split("\\.");
                                    return s[s.length - 1].equals("docx");
                                }

                                @Override
                                public String getDescription() {
                                    return "docx";
                                }
                            });

                            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                                File file = fileChooser.getSelectedFile();
                                String path = file.getPath();
                                if (!path.contains(".docx")) {
                                    path += ".docx";
                                }

                                Writer writer = new DOCXFileWriter(path);
                                writer.write();
                            }
                        }
                    }
                    catch (WriterException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                case SEND -> SenderWindow.getInstance().setVisible(true);
            }
        }
    }
}