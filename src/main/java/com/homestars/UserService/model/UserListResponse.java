package com.homestars.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 
 * @author nchopra
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserListResponse<T> extends BaseResponse {
	private List<T> data;
}
