package com.clickNbuy.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.dto.UserDto;
import com.clickNbuy.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user/auth")
public class AuthController {
	
	AuthService authService;
	
	@PostMapping("/register")
	@ResponseStatus(code =HttpStatus.CREATED)
	public ResponseDto register( @RequestParam  UserDto userDto) {
		return authService.register(userDto);
	}
	
	
}
