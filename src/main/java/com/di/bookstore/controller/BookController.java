package com.di.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.di.bookstore.service.BookService;
import com.di.bookstore.service.UserService;
import com.di.bookstore.util.Jwt;

/**
 * book controller 
 * @author : Sudeep Kumar Katiar
 * @date : 13/04/2020
 * @version :1.0
 */

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private Jwt tokenGenerator;

}
