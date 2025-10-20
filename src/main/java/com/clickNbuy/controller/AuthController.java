package com.clickNbuy.controller;


import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clickNbuy.dto.LoginDto;
import com.clickNbuy.dto.OtpDto;
import com.clickNbuy.dto.PasswordDto;
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
	
	@GetMapping("/resend-otp")
	@ResponseStatus(code =HttpStatus.CREATED)
	public ResponseDto resendOtp(@RequestParam String email) {
		return authService.resendOtp(email);
		
	}
	
	@GetMapping("/forgot-password")
	@ResponseStatus(code =HttpStatus.OK)
	public ResponseDto forgotpassword(@RequestParam String email) {
		return authService.forgotpassword(email);
		
	}
	
	@PostMapping("/forgot-password")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDto forgotPassword(@Valid @RequestBody PasswordDto passwordDto) throws TimeoutException {
		return authService.forgotPassword(passwordDto);
	}
	
	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseDto login(@Valid @RequestBody LoginDto loginDto) {
		System.out.println(loginDto);
		return authService.login(loginDto);
	}
	
	
}
