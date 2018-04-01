package Controllers;

import org.jvnet.mimepull.MIMEMessage;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;

public class MailSender extends HttpServlet {
    private String from;
    private String to;
    private String subject;
    private String message;
    private String login;
    private String password;
    private Session session;
    private Properties props;
    
    public MailSender(String from, String to, String subject, String message, String login, String password) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.login = login;
        this.password = password;
        this.props = new Properties();
    }
    
    public void sendMail() throws MessagingException {
        try {
            Authenticator auth = new SMTPAuthenticator(login, password);
            this.props.setProperty("mail.host", "mail.gmx.net");
            this.props.setProperty("mail.smtp.port", "587");
            this.props.setProperty("mail.smtp.auth", "true");
            this.props.setProperty("mail.smtp.starttls.enable", "true");
            this.session = Session.getInstance(this.props, auth);
        
            MimeMessage msg = new MimeMessage(this.session);
            msg.setText(this.message);
            msg.setSubject(this.subject);
            msg.setFrom(new InternetAddress(this.from));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
            Transport.send(msg);
        
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}