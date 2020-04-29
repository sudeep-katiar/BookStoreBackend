package com.di.bookstore.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.di.bookstore.dto.CustomerDto;
import com.di.bookstore.model.CustomerModel;
import com.di.bookstore.repository.CustomerRepository;
import com.di.bookstore.service.CustomerService;
import com.di.bookstore.util.EmailVerify;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private EmailVerify mail;

	@Override
	public CustomerModel addCustomer(CustomerDto customerdto) {
		
		CustomerModel customer = new CustomerModel(customerdto.getName(), customerdto.getEmail(), customerdto.getPhone(), customerdto.getPincode(),
				customerdto.getLocality(), customerdto.getAddress(), customerdto.getCity(), customerdto.getLandmark());
			
		customerRepository.save(customer);
		
		CustomerModel sendMail = customerRepository.emailFinder(customerdto.getEmail());
		mail.orderMail(sendMail.getEmail());
		
		return customer;
	}

}
