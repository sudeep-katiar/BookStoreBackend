package com.di.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.di.bookstore.dto.LoginDto;
import com.di.bookstore.dto.UserDto;
import com.di.bookstore.model.UserModel;
import com.di.bookstore.responses.Response;
import com.di.bookstore.service.UserService;
import com.di.bookstore.util.Jwt;

import io.swagger.annotations.ApiOperation;

/**
 * user controller 
 * @author : Sudeep Kumar Katiar
 * @date : 11/04/2020
 * @version :1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;

	@Autowired
	private Jwt tokenGenerator;
	
	/**
	 * api for user registration
	 * 
	 * @param userDto
	 * @return response
	 */
	@PostMapping("/register")
	@ApiOperation(value = "Api to Register User for BookStore", response = Response.class)
	public ResponseEntity<Response> register(@Valid @RequestBody UserDto userdto) {

		UserModel user = userservice.register(userdto);
		if (user != null) {

			return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "registration successfull", user));
		} else {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
					.body(new Response(208, "user already exist", userdto));
		}

	}
	
	/**
	 * api for user login
	 * 
	 * @param loginDetails 
	 * @return response
	 */
	@PostMapping("/login")
	@ApiOperation(value = "Api to Login User for BookStore", response = Response.class)
	public ResponseEntity<Response> login(@Valid @RequestBody LoginDto logindto) {
		UserModel userInformation = userservice.login(logindto);
		if (userInformation != null) 
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200, tokenGenerator.createToken(userInformation.getId()), userInformation));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Login failed", 400));
	}

	/**
	 * api for user verification
	 * 
	 * @param token
	 * @return response Entity
	 */
	@GetMapping("/verify/{token}")
	@ApiOperation(value = "Api to Verify User for BookStore", response = Response.class)
	public ResponseEntity<Response> userVerification(@Valid @PathVariable("token") String token) {
		UserModel user = userservice.verify(token);
		if (user != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new Response("verified", 200));
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new Response("not verified", 304));

	}

}
