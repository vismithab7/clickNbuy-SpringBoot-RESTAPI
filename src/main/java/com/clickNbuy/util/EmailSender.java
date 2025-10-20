package com.clickNbuy.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EmailSender {
	
	JavaMailSender mailSender;
	
	@Async
	public void  sendOtp(String email,int otp,String name) {
		
//		System.out.println("The OTP is :" + otp);
		try {
		SimpleMailMessage message =new SimpleMailMessage();
		message.setTo(email);
        message.setSubject("Otp for creating Account with click N buy");
        message.setText("Hello" + name + "Thank you for creating an account with us , Your OTP IS " +otp+",It is valid only for 5 min");
        mailSender.send(message);
		}catch(Exception e) {
		
        System.err.println("The OTP is :" + otp);
		}
		
		
	}

   @Async
	public void sendForgotOtp(String email, int otp, String name) {
		
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(email);
			message.setSubject("Otp for Updating Password");
			message.setText("Hello " + name + " , Your OTP is " + otp+" , It is valid only for 5 mins, You can use this for Updating Password");
			mailSender.send(message);
		} catch (Exception e) {
			System.err.println("The OTP is :" + otp);
		}
		
		
	}

}
