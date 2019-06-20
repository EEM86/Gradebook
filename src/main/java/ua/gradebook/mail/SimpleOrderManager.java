package ua.gradebook.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import ua.gradebook.model.beans.Person;

public class SimpleOrderManager {
    private static final Logger logger = Logger.getLogger(SimpleOrderManager.class);

    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    @Autowired
    public SimpleOrderManager(MailSender mailSender, SimpleMailMessage templateMessage) {
        this.mailSender = mailSender;
        this.templateMessage = templateMessage;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendMessage(Person person) {
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo(person.getEmail());
        msg.setText(
                "Dear " + person.getFirstName()
                        + ", you've received a new message in Gradebook!");
        try{
            this.mailSender.send(msg);
            logger.info("message send");
        }
        catch (MailException ex) {
            logger.error("MailException error ", ex);
        }
    }
}
