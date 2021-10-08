package com.homestars.UserService.controller;

import com.homestars.UserService.model.UserListResponse;
import com.homestars.UserService.model.UserResponse;
import com.homestars.UserService.model.User;
import com.homestars.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author nchopra
 *
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = { RequestMethod.POST })
	public ResponseEntity<?> createUser(@RequestBody User userinput ) {
		UserResponse<com.homestars.UserService.dao.User> user = userService.create(userinput);
		return ResponseEntity.ok(user);
	}

	@RequestMapping(value = "/users", method = { RequestMethod.GET })
	public ResponseEntity<?> getUser() {
		UserListResponse<com.homestars.UserService.dao.User> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}

	//lookup of the user based on username or email
	@RequestMapping(value = "/user", method = { RequestMethod.GET })
	public ResponseEntity<?> lookupUser(@RequestBody User userinput ) {
		UserResponse<com.homestars.UserService.dao.User> user = userService.lookupUser(userinput);
		return ResponseEntity.ok(user);
	}

}
