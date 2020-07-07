package krsu.beks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    @Autowired
    public JavaMailSender emailSender;



    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;




    public void send(String emailTo, String subjec, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subjec);
        mailMessage.setText(message);

        this.emailSender.send(mailMessage);

    }


}
