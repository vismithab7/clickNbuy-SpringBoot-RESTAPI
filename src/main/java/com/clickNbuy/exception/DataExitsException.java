package com.clickNbuy.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DataExitsException extends RuntimeException {

	String message="Data Already Exits";
}
