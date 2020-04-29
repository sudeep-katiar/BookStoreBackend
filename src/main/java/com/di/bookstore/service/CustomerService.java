package com.di.bookstore.service;

import org.springframework.stereotype.Component;

import com.di.bookstore.dto.CustomerDto;
import com.di.bookstore.model.CustomerModel;

@Component
public interface CustomerService {

	CustomerModel addCustomer(CustomerDto customerdto);

}
