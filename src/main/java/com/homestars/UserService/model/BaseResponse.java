package com.homestars.UserService.model;


import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * 
 * @author nchopra
 *
 */
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = -1898217599935891882L;
	private HttpStatus status;
	private String error;
	private String errorCode;
	private Object errorDesc;
}
