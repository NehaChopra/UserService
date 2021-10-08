package com.homestars.UserService.exception;

import com.homestars.UserService.model.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(basePackages = {"com.homestars.UserService"} )
@ControllerAdvice(basePackages = {"com.homestars.UserService"} )
public class ValidatorControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * common place in the system to manage the exception
     *
     * ResponseEntity<?>
     */
    @ResponseBody
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> authExceptionHandler(APIException ex, WebRequest request) {
        logger.error(ex.toString());
        return ResponseEntity.ok(ex.getLocalizedMessage());
    }
} 