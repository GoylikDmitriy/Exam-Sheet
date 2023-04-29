package gui.window;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Window about what program does.
 * @author Goylik D.V.
 */
public final class AboutProgramWindow extends JFrame {
    private static final String TITLE = "About program.";
    private static final int WIDTH = 650;
    private static final int HEIGHT = 500;
    private static final boolean RESIZABLE = false;
    private static AboutProgramWindow instance;

    /**
     * Gives single instance of AboutProgramWindow.
     * @return instance of AboutProgramWindow
     */
    public static AboutProgramWindow getInstance() {
        if (instance == null) {
            instance = new AboutProgramWindow();
        }

        return instance;
    }

    /**
     * Private constructor of AboutProgramWindow.
     */
    private AboutProgramWindow() {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(RESIZABLE);
        this.setLayout(new BorderLayout());
        this.createGUI();
        this.pack();
    }

    /**
     * Creates GUI of AboutProgramWindow.
     */
    private void createGUI() {
        JPanel jpnlTitle = new JPanel();
        JPanel jpnlCenter = new JPanel();
        JPanel jpnlBottom = new JPanel();

        JPanel jpnlImage = new JPanel();
        JPanel jpnlInfo = new JPanel();

        ImageIcon img = new ImageIcon("resources/img/examb.JPG");
        JLabel jlImage = new JLabel(new ImageIcon(img.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        JLabel jlTitle = new JLabel("Ведомость результатов экзамена.");
        JLabel jlInfo = new JLabel(
                "<html>" +
                        "Программа позволяет<br/>" +
                        "1. Создание таблицы с результами экзамена.<br/>" +
                        "2. Загружать список группы из Excel файла.<br/>" +
                        "3. Редактировать таблицу.<br/>" +
                        "4. Сохранить резлутат в вордовский файл по готовому шаблону.<br/>" +
                        "5. Отправить файл по e-mail.<br/>" +
                        "</html>"
        );

        JButton jbExit = new JButton("Exit");

        jpnlImage.setBorder(new BevelBorder(BevelBorder.LOWERED));
        jpnlInfo.setBorder(new BevelBorder(BevelBorder.LOWERED));

        jbExit.addActionListener(e -> this.setVisible(false));

        jpnlImage.add(jlImage);
        jpnlInfo.add(jlInfo);

        jpnlTitle.add(jlTitle);
        jpnlCenter.add(jpnlImage);
        jpnlCenter.add(jpnlInfo);
        jpnlBottom.add(jbExit);

        this.add(jpnlTitle, BorderLayout.NORTH);
        this.add(jpnlCenter, BorderLayout.CENTER);
        this.add(jpnlBottom, BorderLayout.SOUTH);
    }
}