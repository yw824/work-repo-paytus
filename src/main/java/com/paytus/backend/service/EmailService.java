package com.paytus.backend.service;

import com.paytus.backend.dto.FaqDTO;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private JavaMailSender emailSender;

    public void sendMailToAdmin(FaqDTO faqDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(faqDto.getFaqemail());
        message.setTo("paytusintern@gmail.com");
        message.setSubject("페이투스 문의내용입니다");
        message.setText(faqDto.getFaqtext());
        message.setSentDate(faqDto.getFaqdate());
        emailSender.send(message);
    }
}