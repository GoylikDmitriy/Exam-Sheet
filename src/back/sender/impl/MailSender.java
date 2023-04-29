package back.sender.impl;

import back.sender.Sender;
import back.sender.exception.SenderException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Represents a class that implements Sender
 * to send data by e-mail.
 * @author Goylik D.V.
 * @see Sender
 */
public class MailSender implements Sender {
    private final String toAddress;
    private final String subject;
    private final String text;
    private final File attachment;

    /**
     * Constructor of MailSender.
     * @param toAddress where data will be sent.
     * @param subject subject of letter.
     * @param text text of letter.
     * @param attachment attachment file.
     */
    public MailSender(String toAddress, String subject, String text, File attachment) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
    }

    /**
     * Sends data by e-mail.
     * @throws SenderException
     */
    @Override
    public void send() throws SenderException {
        try {
            final String username = "testforcourseproject@gmail.com";
            final String password = "hezfltgcejuxhsgk";

            Properties prop = new Properties();
            prop.put("mail.smtp.auth", true);
            prop.put("mail.smtp.starttls.enable", "true");
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", 587);
            Session session = Session.getInstance(prop, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
            message.setRecipients(Message.RecipientType.TO, toAddresses);
            message.setSubject(subject);

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(text, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            if (attachment != null) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(attachment);
                multipart.addBodyPart(attachmentPart);
            }

            message.setContent(multipart);
            Transport.send(message);

            JOptionPane.showMessageDialog(null, "Mail has been sent.");
        }
        catch (MessagingException e) {
            throw new SenderException(e.getMessage(), e);
        }
        catch (IOException e) {
            throw new SenderException("Wrong attachments.", e);
        }
    }
}