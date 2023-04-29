package gui.window;

import back.sender.Sender;
import back.sender.exception.SenderException;
import back.sender.impl.MailSender;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;

/**
 * Represents a window to send an email message.
 * @author Goylik D.V.
 */
public final class SenderWindow extends JFrame {
    private static final String TITLE = "Send";
    private static final int WIDTH = 375;
    private static final int HEIGHT = 450;
    private static final boolean RESIZABLE = false;
    private static final boolean VISIBLE = true;

    private static File fileToSend;
    private static SenderWindow instance;

    /**
     * Private constructor of SenderWindow.
     */
    private SenderWindow() {
        this.setTitle(TITLE);
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(RESIZABLE);
        this.setVisible(VISIBLE);
        this.setLayout(new BorderLayout());

        this.createGUI();
    }

    /**
     * Gives single instance of SenderWindow.
     * @return instance of SenderWindow
     */
    public static SenderWindow getInstance() {
        if (instance == null) {
            instance = new SenderWindow();
        }

        return instance;
    }

    /**
     * Creates GUI of SenderWindow.
     */
    private void createGUI() {
        JLabel jlTo = new JLabel("To:");
        JLabel jlSubject = new JLabel("Subject:");
        JLabel jlAttachment = new JLabel("attachment");
        jlAttachment.setBorder(new LineBorder(Color.BLACK));

        JTextField jtfTo = new JTextField();
        JTextField jtfSubject = new JTextField();
        JTextField jtfAttachment = new JTextField();

        JTextArea jtaText = new JTextArea();

        JButton jbDelete = new JButton("close");
        jbDelete.setVisible(false);

        JButton jbSend = new JButton("Send");
        JButton jbBack = new JButton("Back");
        JButton jbAttach = new JButton("Attach file");

        jbAttach.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser("resources");
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                fileToSend = jFileChooser.getSelectedFile();
                jlAttachment.setText(fileToSend.getName());
                jbDelete.setVisible(true);
            }
        });

        jbDelete.addActionListener(e -> {
            jlAttachment.setText("attachment");
            jbDelete.setVisible(false);
        });

        jbSend.addActionListener(e -> {
            try {
                Sender sender = new MailSender(jtfTo.getText(), jtfSubject.getText(), jtaText.getText(), fileToSend);
                sender.send();
            }
            catch (SenderException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        jbBack.addActionListener(e -> {
            this.setVisible(false);
        });

        Dimension dim = new Dimension(125, 25);
        Font font = new Font("Times New Roman", Font.BOLD, 15);

        jlTo.setPreferredSize(dim);
        jlSubject.setPreferredSize(dim);
        jlAttachment.setPreferredSize(dim);
        jlTo.setFont(font);
        jlSubject.setFont(font);
        jlAttachment.setFont(font);

        dim = new Dimension(200, 25);
        jtfTo.setPreferredSize(dim);
        jtfSubject.setPreferredSize(dim);
        jtfAttachment.setPreferredSize(dim);
        jtfTo.setFont(font);
        jtfSubject.setFont(font);
        jtfAttachment.setFont(font);

        jtaText.setLineWrap(true);
        jtaText.setPreferredSize(new Dimension(WIDTH - 50, 250));

        JPanel jpnlTo = new JPanel(new FlowLayout());
        JPanel jpnlSubject = new JPanel(new FlowLayout());
        JPanel jpnlAttachment = new JPanel(new FlowLayout());
        JPanel jpnlText = new JPanel();
        JPanel jpnlTop = new JPanel(new BorderLayout());
        JPanel jpnlMiddle = new JPanel();
        JPanel jpnlBottom = new JPanel(new BorderLayout());

        JPanel jpnlToInner = new JPanel(new BorderLayout());
        JPanel jpnlSubjectInner = new JPanel(new BorderLayout());
        JPanel jpnlAttachmentInner = new JPanel();
        JPanel jpnlMainButtonsInner = new JPanel();

        JPanel jpnlDelete = new JPanel();
        jpnlDelete.add(jlAttachment);
        jpnlDelete.add(jbDelete);

        jpnlToInner.add(jlTo, BorderLayout.WEST);
        jpnlToInner.add(jtfTo, BorderLayout.EAST);
        jpnlTo.add(jpnlToInner);
        jpnlSubjectInner.add(jlSubject, BorderLayout.WEST);
        jpnlSubjectInner.add(jtfSubject, BorderLayout.EAST);
        jpnlSubject.add(jpnlSubjectInner);
        jpnlAttachmentInner.add(jpnlDelete);
        jpnlAttachmentInner.add(jbAttach);
        jpnlAttachment.add(jpnlAttachmentInner);
        jpnlText.add(jtaText);
        jpnlTop.add(jpnlTo, BorderLayout.NORTH);
        jpnlTop.add(jpnlSubject, BorderLayout.CENTER);
        jpnlMiddle.add(jtaText);
        jpnlMainButtonsInner.add(jbSend);
        jpnlMainButtonsInner.add(jbBack);
        jpnlBottom.add(jpnlAttachment, BorderLayout.NORTH);
        jpnlBottom.add(jpnlMainButtonsInner, BorderLayout.SOUTH);

        this.add(jpnlTop, BorderLayout.NORTH);
        this.add(jpnlMiddle, BorderLayout.CENTER);
        this.add(jpnlBottom, BorderLayout.SOUTH);
    }
}