package com.clickNbuy.impl;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clickNbuy.dao.UserDao;
import com.clickNbuy.dto.OtpDto;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.dto.UserDto;
import com.clickNbuy.entity.Role;
import com.clickNbuy.entity.User;
import com.clickNbuy.exception.DataExitsException;
import com.clickNbuy.service.AuthService;
import com.clickNbuy.util.EmailSender;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor

public class AuthServiceImpl implements AuthService {
	
	UserDao userDao;
	
	PasswordEncoder encoder;
	EmailSender emailsender;

	@Override
	public ResponseDto register(UserDto userDto) {
		
		
		
		if(userDao.isEmailUnique(userDto.getEmail()) && userDao.isMobileUnique(userDto.getMobile())) {
			
			int otp=new Random().nextInt(100000,1000000);
			
			emailsender.sendOtp(userDto.getEmail(),otp,userDto.getName());
			
			userDao.saveUser(new User(null, userDto.getName(), userDto.getEmail(),encoder.encode(userDto.getPassword()), userDto.getMobile(), null, otp,LocalDateTime.now().plusMinutes(5), Role.valueOf("ROLE_" + userDto.getRole().toUpperCase()), false));
			return new ResponseDto("Otp sent success, Verify within 5 minutes ", userDto);
		}else {
			if(!userDao.isEmailUnique(userDto.getEmail()))
					throw new DataExitsException("Email Alreday Exits : "+ userDto.getEmail());
			else
				throw new DataExitsException("Mobile Alreday Exits : "+ userDto.getMobile());
		}
		
	}

	@Override
	public ResponseDto verifyOtp(OtpDto otpDto) throws TimeoutException {
	User user=userDao.findByEmail(otpDto.getEmail());
	if(LocalDateTime.now().isBefore(user.getOtpExpiryTime())) {
		if(otpDto.getOtp()==user.getOtp()) {
			user.setStatus(true);
			user.setOtp(0);
			user.setOtpExpiryTime(null);
			userDao.saveUser(user);
			return new ResponseDto("Account Created Success", user);
		}else {
			throw new InputMismatchException("Otp miss match, Try Again");
		      }
	     }else {
		throw new TimeoutException("Otp Expired, Resend Otp and Try Again");
	
		}	
	}
	
	
	@Override
	public ResponseDto resendOtp(String email) {
	User user=userDao.findByEmail(email);
	int otp=new Random().nextInt(100000,1000000);
	emailsender.sendOtp(user.getEmail(), otp, user.getName());
	
	user.setOtp(otp);
	user.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5));
	userDao.saveUser(user);
	Map<String, String> map = new HashMap<String, String>();
	map.put("email", email);
	map.put("name", user.getName());
	return new ResponseDto("Otp Resent Success valid only for 5 minutes", map);
	}
	
	

}





