package com.clickNbuy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DataNotFoundException extends RuntimeException {
	
	String message ="Data Not Found";
	
	

}
