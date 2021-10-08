package com.homestars.UserService.exception;

import com.homestars.UserService.util.ErrorType;
import org.springframework.http.HttpStatus;

import java.util.Map;

/**
 * 
 * @author nchopra
 *
 */
public class IntenalServerError extends APIException {

	public IntenalServerError(String message) {
		super(message);
	}

	public HttpStatus getHttpStatus() {
		return HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public ErrorType getErrorType() {
		return ErrorType.DATA_FETCHING_EXCEPTION;
	}

}
