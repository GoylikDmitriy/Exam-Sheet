package gui.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Start window that represents title page.
 * @author Goylik D.V.
 */
public class SplashWindow extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;
    private static final boolean VISIBLE = true;

    /**
     * Splash window constructor.
     */
    public SplashWindow() {
        this.dispose();
        this.setUndecorated(true);
        this.setSize(WIDTH, HEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(VISIBLE);

        this.createGUI();

        this.addMouseMotionListener(new MouseMotionListener() {
            private int xDrag, yDrag, xPress, yPress;

            @Override
            public void mouseDragged(MouseEvent e) {
                xDrag = e.getX();
                yDrag = e.getY();

                JFrame jFrame = (JFrame) e.getSource();
                jFrame.setLocation(jFrame.getLocation().x+xDrag-xPress,
                        jFrame.getLocation().y+yDrag-yPress);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                xPress = e.getX();
                yPress = e.getY();
            }
        });
    }

    /**
     * Creates a GUI of the SplashWindow.
     */
    public void createGUI() {
        // setting panels
        JPanel jpnlMain = new JPanel(new GridLayout(4, 1));
        JPanel jpnlTop = new JPanel(new BorderLayout());
        JPanel jpnlTitle = new JPanel(new BorderLayout());
        JPanel jpnlMiddle = new JPanel(new GridLayout(1, 2, 15, 0));
        JPanel jpnlBottom = new JPanel(new BorderLayout());

        // setting top panel
        Font mainFont = new Font("Times New Roman", Font.BOLD, 14);

        JLabel jlBNTU = new JLabel("Белорусский национальный технический университет");
        jlBNTU.setHorizontalAlignment(SwingConstants.CENTER);
        jlBNTU.setFont(mainFont);
        JPanel jpnlBNTU = new JPanel();
        jpnlBNTU.setPreferredSize(new Dimension(WIDTH, 30));
        jpnlBNTU.add(jlBNTU);

        JLabel jlFac = new JLabel("Факультет информационных технологий и робототехники");
        jlFac.setHorizontalAlignment(SwingConstants.CENTER);
        jlFac.setFont(mainFont);
        JPanel jpnlFac = new JPanel();
        jpnlFac.setPreferredSize(new Dimension(WIDTH, 25));
        jpnlFac.add(jlFac);

        JLabel jlDep = new JLabel("Кафедра программаного обеспечения информационных систем и технологий");
        jlDep.setHorizontalAlignment(SwingConstants.CENTER);
        jlDep.setFont(mainFont);
        JPanel jpnlDep = new JPanel();
        jpnlDep.setPreferredSize(new Dimension(WIDTH, 73));
        jpnlDep.add(jlDep);

        jpnlTop.add(jpnlBNTU, BorderLayout.NORTH);
        jpnlTop.add(jpnlFac, BorderLayout.CENTER);
        jpnlTop.add(jpnlDep, BorderLayout.SOUTH);

        // setting title panel
        JLabel jlCW = new JLabel("Курсовая работа");
        jlCW.setHorizontalAlignment(SwingConstants.CENTER);
        jlCW.setFont(new Font("Times New Roman", Font.BOLD, 18));
        JPanel jpnlCW = new JPanel();
        jpnlCW.setPreferredSize(new Dimension(WIDTH, 30));
        jpnlCW.add(jlCW);

        JLabel jlSub = new JLabel("по дисциплине \"Программирование на языке Java\"");
        jlSub.setHorizontalAlignment(SwingConstants.CENTER);
        jlSub.setFont(mainFont);

        JLabel jlTopic = new JLabel("Ведомость результатов экзамена");
        jlTopic.setHorizontalAlignment(SwingConstants.CENTER);
        jlTopic.setFont(new Font("Times New Roman", Font.BOLD, 20));
        JPanel jpnlTopic = new JPanel();
        jpnlTopic.setPreferredSize(new Dimension(WIDTH, 70));
        jpnlTopic.add(jlTopic);

        jpnlTitle.add(jpnlCW, BorderLayout.NORTH);
        jpnlTitle.add(jlSub, BorderLayout.CENTER);
        jpnlTitle.add(jpnlTopic, BorderLayout.SOUTH);

        // setting middle panel
        JPanel jpnlMiddleLeft = new JPanel();
        JPanel jpnlMiddleRight = new JPanel(new GridLayout(2, 1, 30, 0));

        // setting left panel
        ImageIcon img = new ImageIcon("resources/img/examb.JPG");
        JLabel jlImage = new JLabel(new ImageIcon(img.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        jpnlMiddleLeft.add(jlImage);

        // setting right panel
        JLabel jlStudent = new JLabel("<html>Выполнил: студент группы 10702320" +
                "<br/>Гойлик Дмитрий Валерьевич<html>");
        jlStudent.setFont(mainFont);
        JLabel jlTeacher = new JLabel("<html>Преподаватель: к.ф.-м.н., доц." +
                "<br/>Сидорик Валерий Владимирович<html>");
        jlTeacher.setFont(mainFont);
        jpnlMiddleRight.add(jlStudent);
        jpnlMiddleRight.add(jlTeacher);

        jpnlMiddle.add(jpnlMiddleLeft);
        jpnlMiddle.add(jpnlMiddleRight);

        // setting bottom panel
        JLabel jlCity = new JLabel("Минск 2023");
        jlCity.setFont(mainFont);
        jlCity.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel jpnlCity = new JPanel();
        jpnlCity.setPreferredSize(new Dimension(WIDTH, 20));
        jpnlCity.add(jlCity);

        JPanel jpnlButtons = new JPanel(new GridLayout(1, 2, 15, 0));
        jpnlButtons.setPreferredSize(new Dimension(WIDTH - 20, 75));
        jpnlButtons.setBorder(new EmptyBorder(10, 10, 20, 10));

        JButton jbNext = new JButton("Далее");
        jbNext.addActionListener(e -> {
            this.dispose();
            new MainWindow();
        });

        JButton jbExit = new JButton("Выход");
        jbExit.addActionListener(e -> this.dispose());

        jpnlButtons.add(jbNext);
        jpnlButtons.add(jbExit);

        jpnlBottom.add(jpnlCity, BorderLayout.NORTH);
        jpnlBottom.add(jpnlButtons, BorderLayout.SOUTH);

        // setting main panel
        jpnlMain.add(jpnlTop);
        jpnlMain.add(jpnlTitle);
        jpnlMain.add(jpnlMiddle);
        jpnlMain.add(jpnlBottom);
        jpnlMain.setPreferredSize(new Dimension(700, 500));
        this.add(jpnlMain);
    }
}