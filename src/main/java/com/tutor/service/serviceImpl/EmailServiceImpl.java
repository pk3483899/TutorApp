package com.tutor.service.serviceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import static com.tutor.service.serviceImpl.EmailVerificationServiceImpl.emailOtpMapping;



@Service
public class EmailServiceImpl {


    private JavaMailSender javaMailSender;
    private UserServiceImpl userService;

    //Constructor based dependency Injection
    public EmailServiceImpl(JavaMailSender javaMailSender, UserServiceImpl userService) {
        this.javaMailSender= javaMailSender;
        this.userService=userService;
    }


    //It will generate a random OTP
    public String generateOtp() {
//        It generates random 6-digit number by using Random()..
        return String.format("%06d", new java.util.Random().nextInt(1000000));
    }

    public Map<String, String> sendOtpEmail(String email) {
        String otp = generateOtp();//generates an otp
        emailOtpMapping.put(email,otp);//storing the otp in hashmap
        sendEmail(email, "OTP for email varification", "Your OTP is : "+otp);
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "OTP sent successfully");
        return response;
    }

    public void sendEmail(String to, String subject, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("abc@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }
}

