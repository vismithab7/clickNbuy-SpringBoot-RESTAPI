package com.clickNbuy.util;

import org.springframework.stereotype.Component;

@Component
public class EmailSender {
	public void  sendOtp(String email,int otp,String name) {
		System.out.println("The OTP is :" + otp);
		
	}

}
