package com.clickNbuy.service;

import com.clickNbuy.dto.ResponseDto;
import com.clickNbuy.dto.UserDto;

public interface AuthService {

	ResponseDto register(UserDto userDto);
	

}
