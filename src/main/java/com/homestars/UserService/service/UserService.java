package com.homestars.UserService.service;

import com.homestars.UserService.exception.APIException;
import com.homestars.UserService.exception.BadRequestError;
import com.homestars.UserService.exception.IntenalServerError;
import com.homestars.UserService.exception.NotFoundError;
import com.homestars.UserService.model.UserListResponse;
import com.homestars.UserService.model.UserResponse;
import com.homestars.UserService.model.User;
import com.homestars.UserService.repository.UserRepository;
import com.homestars.UserService.util.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author nchopra
 *
 */
@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TransactionTemplate jpaTransactionTemplate;

	@Autowired
	private JsonHelper JsonHelper;
	/**
	 * create a user
	 *
	 * @return User
	 * @param userInput
	 */
	public UserResponse<com.homestars.UserService.dao.User> create(User userInput) throws APIException {
		UserResponse response = null;
		try {
			//validate user
			com.homestars.UserService.dao.User user = com.homestars.UserService.dao.User.builder().username(userInput.getUsername()).email(userInput.getEmail()).build();

			if(!ObjectUtils.isEmpty(user)) {
				response = UserResponse.builder().data(userRepository.save(user)).build();
			}
		}catch(Exception e) {
			LOGGER.error(String.format("Error while creating a user : %s", userInput.getUsername()));
			throw new BadRequestError("Bad request");
		}
		return response;
	}

	/**
	 * lookup a user either by email/username
	 *
	 * @return UserResponse<User>
	 * @param userInput
	 */
	public UserResponse<com.homestars.UserService.dao.User> lookupUser(User userInput) {
		UserResponse response = null;
		Optional<com.homestars.UserService.dao.User> users = null;
		try {
			if(StringUtils.isNotEmpty(userInput.getEmail())){
				users = findUserByEmail(userInput.getEmail());
			}else if (StringUtils.isNotEmpty(userInput.getUsername())){
				users = findUserByUsername(userInput.getUsername());
			}
		} catch (Exception e) {
			LOGGER.error(String.format("Error while looking up a user : %s", userInput.getUsername()));
			throw new NotFoundError("Not Found");
		}

		if (!ObjectUtils.isEmpty(users)) {
			response = UserResponse.builder().data(users.get()).build();
		}
		return response;
	}

	/**
	 * lookup a user by username
	 *
	 * @return Optional<User>
	 * @param username
	 */
	private Optional<com.homestars.UserService.dao.User> findUserByUsername(String username){
		UserResponse<com.homestars.UserService.dao.User> response = new UserResponse<com.homestars.UserService.dao.User>();
		Optional<com.homestars.UserService.dao.User> users =
				jpaTransactionTemplate.execute(txn -> {
					Optional<com.homestars.UserService.dao.User> dbUsers = null;
					try {
						dbUsers = userRepository.findByUsername(username);
						if (ObjectUtils.isEmpty(dbUsers)) {
							LOGGER.error(String.format("Error while looking up a user by username : %s", username));
							throw new NotFoundError("Not Found");
						}
						return dbUsers;
					} catch (Exception ex) {
						LOGGER.error(String.format(" IntenalServerError while looking up a user by username : %s", username));
						throw new IntenalServerError("Internal server error");
					}
				});
		return users;
	}

	/**
	 * lookup a user by email
	 *
	 * @return Optional<User>
	 * @param email
	 */
	private Optional<com.homestars.UserService.dao.User>  findUserByEmail(String email){
		UserListResponse<com.homestars.UserService.dao.User> response = new UserListResponse<com.homestars.UserService.dao.User>();
		Optional<com.homestars.UserService.dao.User> users =
		 jpaTransactionTemplate.execute(txn -> {
		 	Optional<com.homestars.UserService.dao.User> dbUsers = null;
			try {
				dbUsers = userRepository.findByEmail(email);
				if (ObjectUtils.isEmpty(dbUsers)) {
					LOGGER.error(String.format("Error while looking up a user by username : %s", email));
					throw new NotFoundError("Not Found");
				}
				return dbUsers;
			} catch (Exception ex) {
				LOGGER.error(String.format(" IntenalServerError while looking up a user by username : %s", email));
				throw new IntenalServerError("Internal server error");
			}
		});
		return users;
	}

	/**
	 * list users
	 *
	 * @return UserListResponse<User>
	 */
	public UserListResponse<com.homestars.UserService.dao.User> getUsers() {
		UserListResponse response = null;
		List users = null;
		try {
			users = jpaTransactionTemplate.execute(txn -> {
				List<com.homestars.UserService.dao.User> dbUsers = null;
				try {
					dbUsers = userRepository.findAll();
					if (ObjectUtils.isEmpty(dbUsers)) {
						LOGGER.error(String.format("No user records found"));
						throw new NotFoundError("Not Found");
					}
					return dbUsers;
				} catch (Exception ex) {
					LOGGER.error(String.format("IntenalServerError while fetching users"));
					throw new IntenalServerError("Internal server error");
				}
			});
		} catch (Exception e) {
			LOGGER.error(String.format("IntenalServerError while fetching users"));
			throw new IntenalServerError("Internal server error");
		}

		if (!ObjectUtils.isEmpty(users)) {
			response = UserListResponse.builder().data(users).build();
		}
		return response;
	}
}
