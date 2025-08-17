package com.springboot_auth.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    @Value("${user.email}")
    private String email;

    @Async
    public void sendWelcomeEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(toEmail);
        message.setSubject("Welcome to Our Platform");
        message.setText("Hello " + name + ", \n\nThanks for registering with us!\n\nRegards, \nKishor Pandey Developer of Authify Team");
        mailSender.send(message);
    }

    @Async
    public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        Context context = new Context();
        context.setVariable("email", toEmail);
        context.setVariable("otp", otp);

        String process = templateEngine.process("verify-email", context);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(email);
        helper.setTo(toEmail);
        helper.setSubject("Account Verification OTP");
        helper.setText(process, true);
        mailSender.send(message);
    }

    @Async
    public void sendResetOtpEmail(String toEmail, String otp) throws MessagingException {
        Context context = new Context();
        context.setVariable("email", toEmail);
        context.setVariable("otp", otp);

        String process = templateEngine.process("password-reset", context);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(email);
        helper.setTo(toEmail);
        helper.setSubject("Forgot your password");
        helper.setText(process, true);
        mailSender.send(message);
    }
}
