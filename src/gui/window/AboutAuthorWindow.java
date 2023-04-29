package gui.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Window represents information about the author.
 * @author Goylik D.V.
 */
public final class AboutAuthorWindow extends JFrame {
    private static final String TITLE = "About author.";
    private static final int WIDTH = 350;
    private static final int HEIGHT = 500;
    private static final boolean RESIZABLE = false;

    private static AboutAuthorWindow instance;

    /**
     * Gives single instance of AboutAuthorWindow.
     * @return instance of AboutAuthorWindow
     */
    public static AboutAuthorWindow getInstance() {
        if (instance == null) {
            instance = new AboutAuthorWindow();
        }

        return instance;
    }

    /**
     * Private constructor of AboutAuthorWindow.
     */
    private AboutAuthorWindow() {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(RESIZABLE);
        this.setLayout(new BorderLayout());

        this.createGUI();
    }

    /**
     * Creates GUI of AboutAuthorWindow.
     */
    private void createGUI() {
        JPanel jpnlImage = new JPanel(new BorderLayout());
        jpnlImage.setPreferredSize(new Dimension(250, 300));
        jpnlImage.setBorder(new EmptyBorder(15, 30, 10, 30));
        ImageIcon img = new ImageIcon("resources/img/gtkl.jpg");
        JLabel jlImage = new JLabel(new ImageIcon(img.getImage().getScaledInstance(270, 300, Image.SCALE_SMOOTH)));
        jpnlImage.add(jlImage, BorderLayout.CENTER);

        Font font = new Font("Arial", Font.BOLD, 19);
        JPanel jpnlText = new JPanel(new BorderLayout());
        //jpText.setPreferredSize(new Dimension(250, 35));
        JLabel jlAuthor = new JLabel("Author");
        jlAuthor.setHorizontalAlignment(SwingConstants.CENTER);
        jlAuthor.setFont(font);
        JLabel jlStudent = new JLabel("student of 10702320");
        jlStudent.setHorizontalAlignment(SwingConstants.CENTER);
        jlStudent.setFont(font);
        JLabel jlName = new JLabel("Goylik Dmitry");
        jlName.setHorizontalAlignment(SwingConstants.CENTER);
        jlName.setFont(font);
        JLabel jlMail = new JLabel("goylik.dmitriy@gmail.com");
        jlMail.setHorizontalAlignment(SwingConstants.CENTER);
        jlMail.setFont(font);

        JPanel jpnlMiddle = new JPanel(new BorderLayout());
        jpnlMiddle.add(jlStudent, BorderLayout.NORTH);
        jpnlMiddle.add(jlName, BorderLayout.CENTER);

        jpnlText.add(jlAuthor, BorderLayout.NORTH);
        jpnlText.add(jpnlMiddle, BorderLayout.CENTER);
        jpnlText.add(jlMail, BorderLayout.SOUTH);

        JPanel jpnlButton = new JPanel(new BorderLayout());
        JButton jbBack = new JButton("Back");
        jbBack.setFont(font);
        jbBack.setPreferredSize(new Dimension(250, 55));
        jbBack.addActionListener(e -> this.setVisible(false));
        jpnlButton.setPreferredSize(new Dimension(250, 55));
        jpnlButton.setBorder(new EmptyBorder(10, 30, 10, 30));
        jpnlButton.add(jbBack, BorderLayout.CENTER);

        this.add(jpnlImage, BorderLayout.NORTH);
        this.add(jpnlText, BorderLayout.CENTER);
        this.add(jpnlButton, BorderLayout.SOUTH);
    }
}