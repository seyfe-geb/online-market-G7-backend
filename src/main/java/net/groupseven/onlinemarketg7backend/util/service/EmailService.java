package net.groupseven.onlinemarketg7backend.util.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void send(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);

        text += "\n\n online market \n com.online-market.com";

        message.setText(text);

        emailSender.send(message);
    }
}