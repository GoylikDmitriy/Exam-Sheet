package back.io.output.impl;

import back.io.exception.WriterException;
import back.io.output.Writer;
import gui.component.provider.JComboBoxProvider;
import gui.component.provider.JTableEnhancedProvider;
import gui.component.provider.JTextFieldLimitedProvider;
import gui.component.provider.JXDatePickerProvider;
import org.apache.poi.xwpf.usermodel.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Writes data into a DOCX file.
 * @author Goylik D.V.
 * @see Writer
 */
public class DOCXFileWriter implements Writer {
    private static final String ACADEMIC_YEAR = "academicyear";
    private static final String GROUP = "group";
    private static final String SUBJECT = "subject";
    private static final String TEACHER = "teacher";
    private static final String DATE = "date";
    private static final String PRESENCE = "presence";
    private static final String TERM = "term";
    private static final String DEPARTMENT = "department";
    private static final String YEAR = "year";
    private static final String NOT_ALLOWED = "notallowed";
    private static final String NUM = "num";
    private static final String HOURS = "hours";
    private static final List<String> grades = new ArrayList<>();

    private final String filePath;

    /**
     * Constructor of DOCXFileWriter.
     * @param filePath path where the file will be saved.
     */
    public DOCXFileWriter(String filePath) {
        this.filePath = filePath;
        initializeGrades();
    }

    private void initializeGrades() {
        grades.add("one");
        grades.add("two");
        grades.add("three");
        grades.add("four");
        grades.add("five");
        grades.add("six");
        grades.add("seven");
        grades.add("eight");
        grades.add("nine");
        grades.add("ten");
        grades.add(NOT_ALLOWED);
    }

    /**
     * Writes data to the file.
     * @throws WriterException
     */
    @Override
    public void write() throws WriterException {
        String path = "resources/docx/template.docx";
        try (
                FileInputStream fis = new FileInputStream(path);
                XWPFDocument doc = new XWPFDocument(fis)
        ) {
            XWPFTable table = doc.getTableArray(0);
            for (int i = 0; i < JTableEnhancedProvider.getRowCount(); i++) {
                XWPFTableRow row = table.createRow();
                for (int j = 0; j < JTableEnhancedProvider.getColumnCount() - 1; j++) {
                    XWPFTableCell cell = row.getCell(j);
                    if (j != 1 && j != 2) {
                        cell.getParagraphArray(0).setAlignment(ParagraphAlignment.CENTER);
                    }

                    Object value = JTableEnhancedProvider.getValueAt(i, j);
                    if (value != null) {
                        String strValue = value.toString();
                        if (j == 1) {
                            String[] s = strValue.split(" ");
                            strValue = s[0] + " " + s[1].charAt(0) + "." + s[2].charAt(0) + ".";
                        }

                        if (j == 3) {
                            String val = (String) JTableEnhancedProvider.getValueAt(i, j + 1);
                            if (!val.equals("не допущен") && !val.equals("не явился")) {
                                strValue += " (" + val + ")";
                            }
                        }

                        XWPFRun run = cell.getParagraphArray(0).createRun();
                        run.setFontSize(10);
                        run.setText(strValue);
                        cell.getParagraphArray(0).addRun(run);
                    }
                }

                XWPFTableCell cell = row.getCell(JTableEnhancedProvider.getColumnCount() - 1);
                XWPFRun run = cell.getParagraphArray(0).createRun();
                run.setFontSize(10);
                run.setText(JXDatePickerProvider.getDateString());
                cell.getParagraphArray(0).addRun(run);
            }

            fillTags(doc);
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                doc.write(fos);
                JOptionPane.showMessageDialog(new JFrame(), "Saved as " + filePath, "Success", JOptionPane.PLAIN_MESSAGE);

            }
            catch (Exception e) {
                throw new WriterException("Cant write in a file.", e);
            }
        }
        catch (FileNotFoundException e) {
            throw new WriterException("Can't find file by this way -> " + filePath, e);
        }
        catch (IOException e) {
            throw new WriterException("Can't open excel file.", e);
        }
        catch (Exception e) {
            throw new WriterException("Something wrong.", e);
        }
    }

    /**
     * Replaces tags with specific values.
     * @param doc DOCS document.
     */
    private void fillTags(XWPFDocument doc) {
        Map<String, Integer> stats = JTableEnhancedProvider.getStats();
        int presence = JTableEnhancedProvider.getRowCount();
        if (stats.containsKey(NOT_ALLOWED)) {
            presence -= stats.get(NOT_ALLOWED);
        }

        String group = JComboBoxProvider.getSelectedItem(JComboBoxProvider.GROUP);
        findByTag(doc, PRESENCE, Integer.toString(presence));
        findByTag(doc, ACADEMIC_YEAR, JXDatePickerProvider.getAcademicYear());
        findByTag(doc, TEACHER, JComboBoxProvider.getSelectedItem(JComboBoxProvider.TEACHER));
        findByTag(doc, SUBJECT, JComboBoxProvider.getSelectedItem(JComboBoxProvider.SUBJECT));
        findByTag(doc, GROUP, group);
        findByTag(doc, DATE, JXDatePickerProvider.getDateString());
        findByTag(doc, TERM, Integer.toString(JXDatePickerProvider.getTerm(group)));
        findByTag(doc, YEAR, Integer.toString(JXDatePickerProvider.getYearOfStudy(group)));
        findByTag(doc, NUM, JTextFieldLimitedProvider.getText(JTextFieldLimitedProvider.EXAM_NUMBER));
        findByTag(doc, HOURS, JTextFieldLimitedProvider.getText(JTextFieldLimitedProvider.HOURS));
        findByTag(doc, DEPARTMENT, "информационных технологий и робототехники");
        for (String grade : grades) {
            int value = 0;
            if (stats.containsKey(grade)){
                value = stats.get(grade);
            }

            findByTag(doc, grade, Integer.toString(value));
        }
    }

    /**
     * Looking for tag and replace it with value.
     * @param doc DOCX document.
     * @param tag tag to search.
     * @param to value to replace with.
     */
    private void findByTag(XWPFDocument doc, String tag, String to) {
        List<XWPFParagraph> paragraphs = doc.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String par = paragraph.getText();
            if (par != null && par.contains(tag)) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (var run : runs) {
                    String text = run.getText(0);
                    if (text != null && text.contains(tag)) {
                        text = text.replace(tag, to);
                        run.setBold(true);
                        run.setText(text, 0);
                        return;
                    }
                }
            }
        }
    }
}