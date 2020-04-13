package com.di.bookstore.service;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.di.bookstore.dto.LoginDto;
import com.di.bookstore.dto.UserDto;
import com.di.bookstore.model.UserModel;

/**
 * This interface has the unimplemented functionality of registering the user
 * and verifying with the identity & login functionality.
 * 
 * @author Sudeep Kumar Katiar
 * @created 11/04/2020
 * @version 1.0
 */

@Component
public interface UserService {

	UserModel register(@Valid UserDto userdto);
	
	UserModel login(LoginDto logindto);

	UserModel verify(String token);

}
