package com.homestars.UserService;

import java.util.Optional;

/**
 * MockDataBuilder : <ADD_CLASS_LEVEL_DESCRIPTION>
 *
 * <p>Created By nchopra
 *
 * <p>Updated By nchopra
 */
public class MockDataBuilder {

  public final static String  EMAIL = "test@gmail.com";
  public final static String  USERNAME = "neha chopra";

  public static Optional<com.homestars.UserService.dao.User> getUser() {
    Optional<com.homestars.UserService.dao.User> user = Optional.of(
            com.homestars.UserService.dao.User.builder().username(USERNAME).email(EMAIL).build());
    return user;
  }
}
