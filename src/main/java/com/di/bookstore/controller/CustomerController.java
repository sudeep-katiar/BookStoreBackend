package com.di.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.di.bookstore.dto.CustomerDto;
import com.di.bookstore.model.CustomerModel;
import com.di.bookstore.responses.Response;
import com.di.bookstore.service.CustomerService;

import io.swagger.annotations.ApiOperation;

/**
 * book controller
 * 
 * @author : Sudeep Kumar Katiar
 * @date : 27/04/2020
 * @version :1.0
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	/*
	 * API to add customer
	 */
	@PostMapping("/customerAdd")
	@ApiOperation(value = "Api to add customer for BookStore", response = Response.class)
	private ResponseEntity<Response> addCustomer(@RequestBody CustomerDto customerdto)
			throws Exception {

		CustomerModel customer = customerService.addCustomer(customerdto);
		
		return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Order is Confirmed Successfully", customer));

	}

}
