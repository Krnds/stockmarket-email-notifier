package com.karinedias.financialemailnotifier.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class MailBuilder {

    @Autowired
    JavaMailSender javaMailSender;

    public void send(String from, String to, String subject, String body) {
        MimeMessagePreparator preparator = mimeMessage -> {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(body);
        };
        javaMailSender.send(preparator);
    }
}
