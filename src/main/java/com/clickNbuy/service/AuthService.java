package com.clickNbuy.service;

import java.util.concurrent.TimeoutException;

import com.clickNbuy.dto.LoginDto;
import com.clickNbuy.dto.OtpDto;
import com.clickNbuy.dto.PasswordDto;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.dto.UserDto;






public interface AuthService {

	ResponseDto register(UserDto userDto);

	ResponseDto verifyOtp(OtpDto otpDto) throws TimeoutException;

	ResponseDto resendOtp(String email);
	
	ResponseDto forgotpassword(String email);



	ResponseDto forgotPassword( PasswordDto passwordDto) throws TimeoutException;

	ResponseDto login( LoginDto loginDto);

	

	


	

}
