package Controllers;

import org.apache.commons.mail.EmailException;
import org.jvnet.mimepull.MIMEMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.MultiPartEmail;

public class MailSender extends HttpServlet {
    private static final String USERNAME = "tighshitrecords@gmail.com";
    private static final String PASSWORD = "P6NJiJxN7og5mwqvxlJF";
    private String from;
    private String to;
    private String subject;
    private String message;
    private Session session;
    private Properties props;
    private String host;
    
    public MailSender(String to, String subject, String message) {
        this.from = "tightshitrecords@gmail.com";
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.props = System.getProperties();
        this.host = "smtp.gmail.com";
    }
    
    public void sendMail() {
        this.props.put("mail.smtp.auth", "true");
        this.props.put("mail.smtp.host", "smtp.gmail.com");
        this.props.put("mail.smtp.port", "587");
        this.props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(this.props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        MailSender.USERNAME,
                        MailSender.PASSWORD
                );
            }
        });
        
        try {
            Message message = new MimeMessage(session);
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(this.from));
            message.setRecipient(Message.RecipientType.TO,
                                 new InternetAddress(this.to));
            message.setSubject(this.subject);
            message.setText(this.message);
            
            Transport.send(message, MailSender.USERNAME, MailSender.PASSWORD);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}