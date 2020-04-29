package com.di.bookstore.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.di.bookstore.model.CustomerModel;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

	@Query(value="Select * from customer where email = :email",nativeQuery = true)
	public CustomerModel emailFinder(String email);

}
