package com.homestars.UserService.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author nchopra
 *
 */
public class APIException extends RuntimeException {

	public APIException() {
	}

	public APIException(String message) {
		super(message);
	}

	public APIException(String message, Throwable cause) {
		super(message, cause);
	}

	public APIException(Throwable cause) {
		super(cause);
	}

}
