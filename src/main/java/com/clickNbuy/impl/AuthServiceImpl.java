package com.clickNbuy.impl;
import java.time.LocalDateTime;
import java.util.Random;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.clickNbuy.dao.UserDao;
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
			
			userDao.saveUser(new User(null, userDto.getName(), userDto.getEmail(),encoder.encode(userDto.getPassword()), userDto.getMobile(), null, otp,LocalDateTime.now().plusMinutes(5), Role.valueOf("ROLE_" + userDto.getRole()), false));
			return new ResponseDto("Otp sent success, Verify withi 5 minutes ", userDto);
		}else {
			if(!userDao.isEmailUnique(userDto.getEmail()))
					throw new DataExitsException("Email Alreday Exits : "+ userDto.getEmail());
			else
				throw new DataExitsException("Mobile Alreday Exits : "+ userDto.getMobile());
		}
		
	}
	

}





