package com.di.bookstore.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.di.bookstore.model.UserModel;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<UserModel, Long> {

	@Query(value="Select * from user where email = :email",nativeQuery = true)
	public UserModel findEmail(String email);
	
	@Query(value = "select * from user where id = :id", nativeQuery = true)
	UserModel findbyId(long id);

	@Modifying
	@Query(value="Insert into user (creator_stamp,email,firstname,is_verified,lastname,mobile,password,username) values (:creator_stamp,:email,:firstname,:is_verified,:lastname,:mobile,:password,:username)",nativeQuery = true)
	void insertdata(Date creator_stamp,String email,String firstname,boolean is_verified,String lastname,String mobile,String password,String username);

	@Modifying
	@Query(value="update user set is_verified = true where id = :id", nativeQuery = true)
	void verify(long id);

}
