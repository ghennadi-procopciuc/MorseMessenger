package backend;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;


public class GMail {

    private static Logger logger = Logger.getLogger(GMail.class);

    public static void main(String[] args) {
        try {

            // setup the mail server properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            
            // set up the message
            Session session = Session.getInstance(props);
            
            Message message = new MimeMessage(session);
            
            // add a TO address
            message.setRecipients(Message.RecipientType.TO, 
            			InternetAddress.parse("liviu.andrei.ioan@gmail.com"));
            
            
            // add a multiple CC addresses
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("unix140@yahoo.com, liviu_ioan@yahoo.com"));

            message.setSubject("Welcome to Java");
            message.setContent("Hi, im testing a new way to send emails via java.", "text/plain");
                   
            Transport transport = session.getTransport("smtp");
            
            transport.connect("smtp.gmail.com", 587, "liviu.andrei.ioan", "pass");
            transport.sendMessage(message, message.getAllRecipients());
            logger.error("successfully send email");
        } catch (Exception e) {
            logger.error(e, e);
        }
    }
}