package com.homestars.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 
 * @author nchopra
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse<T> extends BaseResponse {
	private T data;
}
