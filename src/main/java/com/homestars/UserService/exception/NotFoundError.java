package com.homestars.UserService.exception;

import com.homestars.UserService.util.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * 
 * @author nchopra
 *
 */
public class NotFoundError extends APIException {

	public NotFoundError(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}

	public ErrorType getErrorType() {
		return ErrorType.VALIDATION_ERROR;
	}

}
