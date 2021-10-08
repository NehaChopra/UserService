package com.homestars.UserService.exception;

import com.homestars.UserService.util.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * 
 * @author nchopra
 *
 */
public class BadRequestError extends APIException {

	public BadRequestError(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.BAD_REQUEST;
	}

	public ErrorType getErrorType() {
		return ErrorType.VALIDATION_ERROR;
	}

}
