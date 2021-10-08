package com.homestars.UserService.service;

import com.homestars.UserService.MockDataBuilder;
import com.homestars.UserService.model.User;
import com.homestars.UserService.model.UserResponse;
import com.homestars.UserService.repository.UserRepository;
import com.homestars.UserService.util.JsonHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author nchopra
 *
 */
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService = Mockito.spy(new UserService());

	@Mock
	private TransactionTemplate jpaTransactionTemplate;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLookUpUserBasedOnEmail(){
		Optional
				<com.homestars.UserService.dao.User> user = MockDataBuilder.getUser();
		Mockito.when(userRepository.findByEmail(Mockito.eq(MockDataBuilder.EMAIL))).thenReturn(user);
		Mockito.when(jpaTransactionTemplate.execute(Mockito.any())).thenReturn(user);
		UserResponse<com.homestars.UserService.dao.User> result = userService.lookupUser(User.builder().email(MockDataBuilder.EMAIL).build());
		assertNotNull(result);
		assertEquals(result.getData().getEmail(), user.get().getEmail());
		assertEquals(result.getData().getUsername(), user.get().getUsername());
	}

	@Test
	public void testLookUpUserBasedOnUserName(){
		Optional
				<com.homestars.UserService.dao.User> user = MockDataBuilder.getUser();
		Mockito.when(userRepository.findByUsername(Mockito.eq(MockDataBuilder.USERNAME))).thenReturn(user);
		Mockito.when(jpaTransactionTemplate.execute(Mockito.any())).thenReturn(user);
		UserResponse<com.homestars.UserService.dao.User> result = userService.lookupUser(User.builder().username(MockDataBuilder.USERNAME).build());
		assertNotNull(result);
		assertEquals(result.getData().getEmail(), user.get().getEmail());
		assertEquals(result.getData().getUsername(), user.get().getUsername());
	}

	@Test
	public void testLookUpUserBasedwithMissingUsername(){
		Mockito.when(userRepository.findByUsername(Mockito.eq(""))).thenReturn(null);
		Mockito.when(jpaTransactionTemplate.execute(Mockito.any())).thenReturn(null);
		UserResponse<com.homestars.UserService.dao.User> result = userService.lookupUser(User.builder().username("").build());
		assertNull(result);
	}

	@Test
	public void testLookUpUserBasedwithMissingEmail(){
		Mockito.when(userRepository.findByEmail(Mockito.eq(""))).thenReturn(null);
		Mockito.when(jpaTransactionTemplate.execute(Mockito.any())).thenReturn(null);
		UserResponse<com.homestars.UserService.dao.User> result = userService.lookupUser(User.builder().email("").build());
		assertNull(result);
	}
}
