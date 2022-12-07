package com.example.oryeqrzh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("{spring.mail.username}")
    private String MESSAGE_FROM;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(String to) throws Exception {
        SimpleMailMessage message;
        try {
            message = new SimpleMailMessage();
            message.setFrom("amugyiselfelejtem@gmail.com");
            message.setTo(to);
            message.setSubject("Teszt uzenet targya");
            message.setText("Ez egy sikerese teszt tartalma");
            javaMailSender.send(message);
        } catch (Exception e){
            System.err.println("Email hiba " + e.getMessage());
        }
    }
}
