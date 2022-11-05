package com.berkaayydemir.webfluxinfo.exception;

import com.berkaayydemir.webfluxinfo.dto.InputFailedValidationResponse;

public class InputValidationException extends RuntimeException {
	private static final String errorMessage = "allowed range is 10 - 20";
	private static final int errorCode = 100;
	private static int input;

	public InputValidationException( int input) {
		super(errorMessage);
		this.input = input;
	}

	public int getInput() {
		return input;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public int getErrorCode(){
		return errorCode;
	}
}
