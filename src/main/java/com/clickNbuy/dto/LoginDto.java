package com.clickNbuy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
	
	
	@NotEmpty(message ="Email is required")
	@Email(message ="Email Should be proper")
	private String email;
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password should contain one uppercase lowercase special character and number and min 8 digits")
	private String password;
	

}
