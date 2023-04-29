package gui.window;

import back.io.exception.ReaderException;
import back.io.input.Reader;
import back.io.input.provider.FileReaderProvider;
import gui.component.JTextFieldLimited;
import gui.component.provider.JComboBoxProvider;
import gui.component.provider.JTableEnhancedProvider;
import gui.component.JTableEnhanced;
import gui.component.provider.JTextFieldLimitedProvider;
import gui.component.provider.JXDatePickerProvider;
import gui.listener.MainButtonsListener;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Main window where all functionality
 * of the application is located.
 * @author Goylik D.V.
 */
public class MainWindow extends JFrame {
    private static final String TITLE = "Examination score sheet.";
    private static final int WIDTH = 800;
    private static final int HEIGHT = 700;
    private static final boolean RESIZABLE = false;
    private static final boolean VISIBLE = true;

    /**
     * Constructor of MainWindow.
     */
    public MainWindow() {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(RESIZABLE);
        this.setVisible(VISIBLE);
        this.setLayout(new BorderLayout());
        this.setIconImage(new ImageIcon("resources/img/examb.JPG").getImage());

        this.createGUI();
    }

    /**
     * Creates GUI of MainWindow.
     */
    private void createGUI() {
        createMenu();

        Font font = new Font("Arial", Font.PLAIN, 17);

        JPanel jpnlMain = new JPanel(new BorderLayout());
        JPanel jpnlTop = new JPanel(new BorderLayout());

        JPanel jpnlES = new JPanel(new FlowLayout());
        JLabel jlES = new JLabel("Экзаменационная ведомость №");
        jlES.setFont(new Font("Arial", Font.BOLD, 19));
        JTextFieldLimited jtfNumber = JTextFieldLimitedProvider.getJTFExamNumber();
        jtfNumber.setPreferredSize(new Dimension(80, 25));
        jtfNumber.setFont(new Font("", Font.BOLD, 19));

        jpnlES.add(jlES);
        jpnlES.add(jtfNumber);

        JPanel jpnlHours = new JPanel(new BorderLayout());
        JPanel jpnlLecture = new JPanel(new FlowLayout());
        JPanel jpnlPractice = new JPanel(new FlowLayout());

        jpnlLecture.setBorder(new EmptyBorder(0, 10, 0, 0));
        jpnlPractice.setBorder(new EmptyBorder(0, 0, 0,15));

        JLabel jlLecture = new JLabel("Количество часов по дисциплине (практике):");
        jlLecture.setFont(font);
        JTextFieldLimited jtfLecture = JTextFieldLimitedProvider.getJTFHours();
        jtfLecture.setPreferredSize(new Dimension(70, 25));
        jtfLecture.setFont(font);

        JLabel jlPractice = new JLabel("Количество часов по дисциплине (практике):");
        jlPractice.setFont(font);
        JTextFieldLimited jtfPractice = new JTextFieldLimited(3);
        jtfPractice.setPreferredSize(new Dimension(35, 25));
        jtfPractice.setFont(font);

        jpnlLecture.add(jlLecture);
        jpnlLecture.add(jtfLecture);

        jpnlPractice.add(jlPractice);
        jpnlPractice.add(jtfPractice);

        jpnlHours.add(jpnlLecture, BorderLayout.WEST);

        jpnlTop.add(jpnlES, BorderLayout.NORTH);
        jpnlTop.add(jpnlHours, BorderLayout.CENTER);

        JPanel jpnlTable = new JPanel(new BorderLayout());
        JTableEnhanced table = JTableEnhancedProvider.getTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 15, 10, 5));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel jpnlTnD = new JPanel(new BorderLayout());
        JPanel jpnlData = new JPanel();
        jpnlData.setBorder(new EmptyBorder(0, 0, 0,15));
        JLabel jlData = new JLabel("Дата:");
        jlData.setFont(font);
        JXDatePicker datePicker = JXDatePickerProvider.getJXDatePicker();
        jpnlData.add(jlData);
        jpnlData.add(datePicker);

        JPanel jpnlTeacher = new JPanel();
        jpnlTeacher.setBorder(new EmptyBorder(0, 10, 0, 0));
        JLabel jlTeacher = new JLabel("Преподаватель:");
        jlTeacher.setFont(font);
        JComboBox<String> jcbTeacher = JComboBoxProvider.getJCBTeacher();
        new Thread(() -> {
            try {
                String path = "resources/excel/teacher.xlsx";
                Reader reader = FileReaderProvider.getFileReader(path);
                reader.read(JComboBoxProvider::addTeacherFromTableItems);

                path = "resources/excel/subject.xlsx";
                reader = FileReaderProvider.getFileReader(path);
                reader.read(JComboBoxProvider::addSubjectFromTableItems);

                path = "resources/excel/group.xlsx";
                reader = FileReaderProvider.getFileReader(path);
                reader.read(JComboBoxProvider::addGroupFromTableItems);
            }
            catch (ReaderException e) {
                JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }).start();

        jpnlTeacher.add(jlTeacher);
        jpnlTeacher.add(jcbTeacher);

        jpnlTnD.add(jpnlData, BorderLayout.EAST);
        jpnlTnD.add(jpnlTeacher, BorderLayout.WEST);

        JPanel jpnlInfo = new JPanel(new BorderLayout());
        JPanel jpnlGroup = new JPanel();
        jpnlGroup.setBorder(new EmptyBorder(0, 0, 0,15));
        JLabel jlGroup = new JLabel("Группа:");
        jlGroup.setFont(font);
        JComboBox<String> jcbGroup = JComboBoxProvider.getJCBGroup();
        jpnlGroup.add(jlGroup);
        jpnlGroup.add(jcbGroup);

        JPanel jpnlSubject = new JPanel();
        jpnlSubject.setBorder(new EmptyBorder(0, 10, 0, 0));
        JLabel jlSubject = new JLabel("Предмет:");
        jlSubject.setFont(font);
        JComboBox<String> jcbSubject = JComboBoxProvider.getJCBSubject();
        jpnlSubject.add(jlSubject);
        jpnlSubject.add(jcbSubject);

        jpnlInfo.add(jpnlSubject, BorderLayout.WEST);
        jpnlInfo.add(jpnlGroup, BorderLayout.EAST);
        jpnlTable.add(jpnlInfo, BorderLayout.NORTH);
        jpnlTable.add(scrollPane, BorderLayout.CENTER);


        MainButtonsListener listener = new MainButtonsListener();
        JPanel jpnlBottom = new JPanel(new BorderLayout());
        JPanel jpnlButtons = new JPanel(new FlowLayout());
        JButton jbOpen = new JButton("Open");
        jbOpen.addActionListener(listener);
        JButton jbAdd = new JButton("Add");
        jbAdd.addActionListener(listener);
        JButton jbRemove = new JButton("Remove");
        jbRemove.addActionListener(listener);
        JButton jbSave = new JButton("Save");
        jbSave.addActionListener(listener);
        JButton jbSend = new JButton("Send");
        jbSend.addActionListener(listener);
        jpnlButtons.add(jbOpen);
        jpnlButtons.add(jbAdd);
        jpnlButtons.add(jbRemove);
        jpnlButtons.add(jbSave);
        jpnlButtons.add(jbSend);

        jpnlBottom.add(jpnlTnD, BorderLayout.NORTH);
        jpnlBottom.add(jpnlButtons, BorderLayout.CENTER);
        jpnlBottom.setPreferredSize(new Dimension(WIDTH, 100));

        jpnlMain.add(jpnlTop, BorderLayout.NORTH);
        jpnlMain.add(jpnlTable, BorderLayout.CENTER);
        jpnlMain.add(jpnlBottom, BorderLayout.SOUTH);

        this.add(jpnlMain, BorderLayout.CENTER);
    }

    /**
     * Creates Menu of MainWindow.
     */
    private void createMenu() {
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jmFile = new JMenu("File");
        JMenu jmAbout = new JMenu("About");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmiExit.addActionListener(e -> this.dispose());
        JMenuItem jmiAboutAuthor = new JMenuItem("About author");
        jmiAboutAuthor.addActionListener(e -> AboutAuthorWindow.getInstance().setVisible(true));
        JMenuItem jmiAboutProgram = new JMenuItem("About program");
        jmiAboutProgram.addActionListener(e -> AboutProgramWindow.getInstance().setVisible(true));
        jmFile.add(jmiExit);
        jmAbout.add(jmiAboutAuthor);
        jmAbout.add(jmiAboutProgram);
        jMenuBar.add(jmFile);
        jMenuBar.add(jmAbout);
        this.add(jMenuBar, BorderLayout.NORTH);
    }
}