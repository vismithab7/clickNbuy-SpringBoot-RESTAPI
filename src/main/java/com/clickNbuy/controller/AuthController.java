package com.clickNbuy.controller;


import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clickNbuy.dto.OtpDto;
import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.dto.UserDto;
import com.clickNbuy.service.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthController {
	
	AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(code =HttpStatus.CREATED)
	public ResponseDto register( @Valid @RequestBody  UserDto userDto) {
		return authService.register(userDto);
	}
	
	@PostMapping("/verify-otp")
	@ResponseStatus(code =HttpStatus.CREATED)
	public ResponseDto verifyOtp(@RequestBody OtpDto otpDto) throws TimeoutException{
		return authService.verifyOtp(otpDto);
		
	}
	
}
